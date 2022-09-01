package ru.java.springbootwebjsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.java.springbootwebjsp.repositiry.AccountRepository;
import ru.java.springbootwebjsp.model.Account;

import java.util.List;

import static ru.java.springbootwebjsp.util.ValidationUtil.checkNotFoundWithId;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    public Account add(Account account, int userId){
        return accountRepository.save(account, userId);
    }

    public List<Account> getAll(int userId, String typeCurrency){
        return accountRepository.getAll(userId, typeCurrency);
    }

    public Account get(int id, int userId){
        return checkNotFoundWithId(accountRepository.get(id, userId) ,id);
    }

    public Account getByID(int id){
        return accountRepository.getByID(id);
    }

    public int getUserID(int id){
        return accountRepository.getUserID(id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(accountRepository.delete(id, userId), id);
    }

    public Account save(Account account, int userId){
        Assert.notNull(account,"account must not null");
        return checkNotFoundWithId(accountRepository.save(account, userId), account.getId());
    }

}
