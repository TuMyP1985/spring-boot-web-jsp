package ru.java.springbootwebjsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.java.springbootwebjsp.model.BankStatements;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.repositiry.BankstatementRepository;
import ru.java.springbootwebjsp.repositiry.UserRepository;
import ru.java.springbootwebjsp.util.ValidationUtil;

import java.util.List;

@Service
public class BankStatementService {

    @Autowired
    BankstatementRepository bankstatementRepository;

    public List<BankStatements> getAll(){
        return bankstatementRepository.getAll();
    }

    public BankStatements get(int id){
        return ValidationUtil.checkNotFoundWithId(bankstatementRepository.get(id), id);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(bankstatementRepository.delete(id), id);
    }

    public BankStatements save(BankStatements bankStatements, int userId, int accountId){
        Assert.notNull(bankStatements,"bankStatements must not null");
        return ValidationUtil.checkNotFoundWithId(bankstatementRepository.save(bankStatements, userId, accountId), bankStatements.getId());
    }

}
