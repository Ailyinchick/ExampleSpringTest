package hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {

    }

    public Account(int account) {
        this.account = account;
    }

    public Account(int id, int account) {
        this.id = id;
        this.account = account;
    }

    @Override
    public String toString() {
        return this.id + " " + this.account;
    }

    public int getId() {
        return id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}