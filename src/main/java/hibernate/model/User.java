package hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    private String name;
    @Column(name = "surname")
    private String surName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.surName + " : "/* + this.accounts.toString() + " "*/;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public User(String name, String surName) {
        this.name = name;
        this.surName = surName;
        accounts = new ArrayList<Account>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void addAccount(Account account) {
        account.setUser(this);
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}