package ru.java.springbootwebjsp.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.java.springbootwebjsp.model.enums.TypeCurrency;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "accounts")
public class Account extends AbstractBaseEntity{

    @Column(name = "number", nullable = false)
    private int number;

   @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    //@NotNull
    private Date registered;

   @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "typeCurrency", nullable = false)
    private TypeCurrency typeCurrency;

    @Column(name = "value", nullable = false)
    private double value;

//    @Column(name = "user_id", nullable = false)
//    private Integer user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Account(Integer id, int number, Date registered, String description, TypeCurrency typeCurrency) {
        super(id);
        this.number = number;
        this.registered = registered;
        this.description = description;
        this.typeCurrency = typeCurrency;
    }

    public Account(Integer id, int number, String description, TypeCurrency typeCurrency, double value) {
        super(id);
        this.number = number;
        this.description = description;
        this.typeCurrency = typeCurrency;
        this.value = value;
    }

    public Account(int number, String description, TypeCurrency typeCurrency, double value) {
        this(null,number,description,typeCurrency,value);
    }

    public Account(){

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

//    public Integer getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(Integer user_id) {
//        this.user_id = user_id;
//    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number=" + number +
                ", registered=" + registered +
                ", typeCurrency=" + typeCurrency +
                ", description=" + description +
                ", value=" + value +
                "}";
    }

    public TypeCurrency getTypeCurrency() {
        return typeCurrency;
    }

    public void setTypeCurrency(TypeCurrency typeCurrency) {
        this.typeCurrency = typeCurrency;
    }

}
