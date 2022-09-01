package ru.java.springbootwebjsp.repositiry;

import ru.java.springbootwebjsp.model.Account;

import java.util.List;

public interface AccountRepository {
    //null if not found, when update
    Account save(Account account, int userId);

    //false if not found
    boolean delete(int id, int userId);

    //null if not found
    Account get(int id, int userId);

    Account getByID(int id);

    List<Account> getAll(int userId,String typeCurrency);

    Integer getUserID(int id);

}
