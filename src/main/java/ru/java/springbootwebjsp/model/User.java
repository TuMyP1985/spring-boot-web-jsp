package ru.java.springbootwebjsp.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity{

    @Column(name = "name", nullable = false)
    protected String name;

    public User(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

}
