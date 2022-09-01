package ru.java.springbootwebjsp.repositiry.datajpa;

import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.repositiry.AccountRepository;

import java.util.List;

//@Repository
public class DataJpaAccountRepository implements AccountRepository {

    private final CrudAccountRepository crudAccountRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaAccountRepository(CrudAccountRepository crudAccountRepository, CrudUserRepository crudUserRepository) {
        this.crudAccountRepository = crudAccountRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Account save(Account account, int userId) {
        if (!account.isNew() && get(account.getId(), userId)==null){
            return null;
        }
        account.setUser(crudUserRepository.getOne(userId));
        return crudAccountRepository.save(account);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudAccountRepository.delete(id, userId) != 0;
    }

    @Override
    public Account get(int id, int userId) {
        Account account = getByID(id);
        return account.getUser().getId()!=userId?null:account;
    }

    @Override
    public Account getByID(int id) {
        return crudAccountRepository.getOne(id);
    }

    @Override
    public List<Account> getAll(int userId, String typeCurrency) {
        return crudAccountRepository.getAll(userId);
    }

    @Override
    public Integer getUserID(int id) {
        return null;
    }
}
