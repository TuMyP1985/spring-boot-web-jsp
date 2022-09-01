package ru.java.springbootwebjsp.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "bankstatements")
public class BankStatements extends AbstractBaseEntity{

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    private Date registered;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "typeCurrency", nullable = false)
    private TypeCurrency typeCurrency;

    @Column(name = "value", nullable = false)
    private double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;


    public BankStatements(Integer id, String description, TypeCurrency typeCurrency, double value, User user, Account account) {
        super(id);
        this.description = description;
        this.typeCurrency = typeCurrency;
        this.value = value;
        this.user = user;
        this.account = account;
    }

    public BankStatements(String description, TypeCurrency typeCurrency, double value, User user, Account account) {
        this(null, description, typeCurrency, value, user, account);
    }

    public BankStatements(Integer id, String description, TypeCurrency typeCurrency) {
        super(id);
        this.description = description;
        this.typeCurrency = typeCurrency;
    }

    public BankStatements(String description, TypeCurrency typeCurrency) {
        this(null, description, typeCurrency);
    }


    public BankStatements(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeCurrency getTypeCurrency() {
        return typeCurrency;
    }

    public void setTypeCurrency(TypeCurrency typeCurrency) {
        this.typeCurrency = typeCurrency;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
