package ru.java.springbootwebjsp.web;

import ru.java.springbootwebjsp.model.AbstractBaseEntity;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;
    private static String CurrencyInAccountjsp = "";

    public static int selectedUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static String SelectedCurrencyInAccountjsp() {
        return CurrencyInAccountjsp;
    }

    public static void setCurrencyInAccountjsp(String CurrencyInAccountjsp) {
        SecurityUtil.CurrencyInAccountjsp = CurrencyInAccountjsp;
    }
}
