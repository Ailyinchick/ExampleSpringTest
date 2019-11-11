package hibernate.dao;


import hibernate.model.Account;
import hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class DaoUser  {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User foundByID(int id) {
        return sessionFactory.openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<User> findAll() {
        List<User> users = (List<User>) sessionFactory.openSession().createQuery("From User").list();
        return users;
    }

    public String totalBank() {
        Session session = sessionFactory.openSession();
        org.hibernate.procedure.ProcedureCall call = session.createStoredProcedureCall("totalbank");
        org.hibernate.procedure.ProcedureOutputs callOutputs = call.getOutputs();
        org.hibernate.result.Output output;
        while ((output = callOutputs.getCurrent()) != null) { // проверяем наличие выходных значений
            if (output.isResultSet()) { // если выходное значение результат запроса (некоторые процедуры могут возвращать количество обработанных строк, тогда вернет false)
                List<Integer> result = ((org.hibernate.result.ResultSetOutput) output).getResultList();
                return "" + result.get(0);
            }
            if (!callOutputs.goToNext()) // некоторые процедуры могут возвращать несколько последовательных результатов
                break;
        }
        return "";
    }

    public String findRichest() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbfordv", "root", "123qwe");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("call findRichest()");
            while (rs.next()) {
                return rs.getString(1) + " - " + rs.getInt(2);
            }
        } catch (ClassNotFoundException ex1) {
            System.out.println("Class not found EX " + ex1.getMessage());
        } catch (SQLException ex2) {
            System.out.println("SQL EX " + ex2.getMessage());
        }
        return null;
    }

}