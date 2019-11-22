package hibernate.service;


import hibernate.dao.DaoUser;
import hibernate.model.Account;
import hibernate.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Some extends Thread {

    static List<User> outList = new ArrayList<>();

    private int userId;

    public static void main(String[] args) {
        for (int i=1; i<11; i++) {
            new Some(i).start();
        }
    }



    public Some(int id) {
        this.userId = id;
    }

    public void run() {
        System.out.println(findById(userId));
    }


    public User findById(int id) {
        User user = new User();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbfordv", "root", "123qwe");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user where id = " + id);
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurName(rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex1) {
            System.out.println(ex1.getMessage());
        }
        return user;
    }

}
