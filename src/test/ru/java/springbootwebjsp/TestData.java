package ru.java.springbootwebjsp;

import ru.java.springbootwebjsp.model.Account;
import ru.java.springbootwebjsp.model.User;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;
import ru.java.springbootwebjsp.model.paper.RichPaper;

import java.awt.print.Paper;

import static ru.java.springbootwebjsp.model.AbstractBaseEntity.START_SEQ;

public class TestData {
    public static final int USER_ID_1 = START_SEQ;                  //100000
    public static final int ACCOUNT_ID_1 = START_SEQ + 1;           //100003
    public static final User userTest = new User(USER_ID_1,"Иванов Иван");
    public static final Account accountTest = new Account(ACCOUNT_ID_1, "Рубл.счет (Иванов)", TypeCurrency.RUB,173000.12);
    public static final RichPaper paperTest = new RichPaper("SBER", "Сбербанк ПАО");

    {
        accountTest.setUser(userTest);
    }

}
