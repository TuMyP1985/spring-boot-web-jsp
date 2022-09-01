package ru.java.springbootwebjsp.util;

public class DataFromFile {
    private int accountid;
    private String description;
    private double value;
    private String error;

    public DataFromFile(int accountid, String description, double value, String error) {
        this.accountid = accountid;
        this.description = description;
        this.value = value;
        this.error = error;
    }

    public DataFromFile(){};

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
