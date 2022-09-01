package ru.java.springbootwebjsp.repositiry.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.java.springbootwebjsp.model.BankStatements;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.repositiry.BankstatementRepository;
import ru.java.springbootwebjsp.repositiry.UserRepository;
import ru.java.springbootwebjsp.service.BankStatementService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcBankStatementRepository implements BankstatementRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final BeanPropertyRowMapper<BankStatements> ROW_MAPPER = BeanPropertyRowMapper.newInstance(BankStatements.class);

    private final SimpleJdbcInsert insertBankStatement;


    public JdbcBankStatementRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertBankStatement = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("bankStatements")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public BankStatements save(BankStatements bankStatements, int userId, int accountId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", bankStatements.getId())
                .addValue("registered", new Date())
                .addValue("description", bankStatements.getDescription())
                .addValue("typeCurrency", bankStatements.getTypeCurrency().name())
                .addValue("value", bankStatements.getValue())
                .addValue("user_id", userId)
                .addValue("account_id", accountId)
                ;

        if (bankStatements.isNew()) {
            Number newId = insertBankStatement.executeAndReturnKey(map);
            bankStatements.setId(newId.intValue());
        } else {
//            if (namedParameterJdbcTemplate.update("" +
//                    "UPDATE bankstatements " +
//                    "   SET  description=:description " +
//                    " WHERE id=:id " +
//                    "", map) == 0) {
//                return null;
//            }
        }
        return bankStatements;

    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM bankstatements WHERE id=?", id) != 0;
    }

    @Override
    public BankStatements get(int id) {
        List<BankStatements> BankStatements = jdbcTemplate.query("SELECT * FROM bankstatements WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(BankStatements);
    }

    @Override
    public List<BankStatements> getAll() {
        String query = "SELECT * FROM bankstatements ORDER BY id";

        return jdbcTemplate.query(query, ROW_MAPPER);

    }
}
