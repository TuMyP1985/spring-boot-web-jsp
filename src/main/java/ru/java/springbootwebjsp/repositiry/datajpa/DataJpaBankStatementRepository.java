package ru.java.springbootwebjsp.repositiry.datajpa;

import org.springframework.stereotype.Repository;
import ru.java.springbootwebjsp.model.BankStatements;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.repositiry.BankstatementRepository;
import ru.java.springbootwebjsp.repositiry.UserRepository;

import java.util.List;

//@Repository
public class DataJpaBankStatementRepository implements BankstatementRepository {

    private final CrudBankStatementRepository crudRepository;

    public DataJpaBankStatementRepository(CrudBankStatementRepository crudUserRepository) {
        this.crudRepository = crudUserRepository;
    }


    @Override
    public BankStatements save(BankStatements bankStatements, int userId, int accountId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public BankStatements get(int id) {
        return null;
    }

    @Override
    public List<BankStatements> getAll() {
        return crudRepository.findAll();
    }
}
