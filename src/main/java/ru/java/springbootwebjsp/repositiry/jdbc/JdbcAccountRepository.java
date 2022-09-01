package ru.java.springbootwebjsp.repositiry.jdbc;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.java.springbootwebjsp.repositiry.AccountRepository;
import ru.java.springbootwebjsp.model.Account;

import java.util.Date;
import java.util.List;

@Repository
public class JdbcAccountRepository implements AccountRepository {

    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Account> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Account.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertAccount;

    public JdbcAccountRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertAccount = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("accounts")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Account save(Account account, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", account.getId())
                .addValue("number", account.getNumber())
                .addValue("registered", new Date())
                .addValue("description", account.getDescription())
                .addValue("typeCurrency", account.getTypeCurrency().name())
                .addValue("value", account.getValue())
                .addValue("user_id", userId);

        if (account.isNew()) {
            Number newId = insertAccount.executeAndReturnKey(map);
            account.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE accounts " +
                    "   SET number=:number, typeCurrency=:typeCurrency, value=:value, description=:description " +
                    " WHERE id=:id AND user_id=:user_id", map) == 0) {
                return null;
            }
        }
        return account;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM accounts WHERE id=? AND (user_id = ? OR ?=0)", id, userId, userId) != 0;
    }

    @Override
    public Account get(int id, int userId) {
        List<Account> accounts = jdbcTemplate.query(
                "SELECT * FROM accounts WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(accounts);
    }

    @Override
    public Account getByID(int id) {
        List<Account> accounts = jdbcTemplate.query(
                "SELECT * FROM accounts WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(accounts);
    }

    @Override
    public Integer getUserID(int id) {

        Integer userid;
        String sql = "SELECT user_id FROM accounts WHERE id = ?";
        userid = jdbcTemplate.queryForObject(sql, new Object[] {id}, Integer.class);
        return userid;
    }

    @Override
    public List<Account> getAll(int userId, String typeCurrency) {
        //если userId ноль, значит показываем все счета (по всем пользователям)
        //если валюта "", значит показываем все счета (по всем валютам)
        String query = "SELECT * FROM accounts WHERE (user_id=? OR ?=0) AND (typeCurrency=? OR ?) ORDER BY number, user_id DESC";
        return jdbcTemplate.query(query, ROW_MAPPER, userId, userId, typeCurrency, typeCurrency.isEmpty());
    }
}
