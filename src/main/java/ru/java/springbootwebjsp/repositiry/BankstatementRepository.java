package ru.java.springbootwebjsp.repositiry;

import ru.java.springbootwebjsp.model.BankStatements;
import ru.java.springbootwebjsp.model.User;

import java.util.List;

public interface BankstatementRepository {
    //null if not found, when update
    BankStatements save(BankStatements bankStatements, int userId, int accountId);

    //false if not found
    boolean delete(int id);

    //null if not found
    BankStatements get(int id);

    List<BankStatements> getAll();

}
