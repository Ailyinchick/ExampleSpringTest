package hibernate.service;

import hibernate.dao.DaoUser;
import hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private DaoUser daoUser;

    public UserService() {
    }

    public UserService(DaoUser daoUser) {
        this.daoUser = daoUser;
    }


    public String findById(String id) {
        try {
            int outID = Integer.parseInt(id);
            return daoUser.foundByID(outID).toString();
        } catch (ArithmeticException ex) {
            System.out.println("Эксептиона арифметик каст то инт : \n" + ex.getMessage());
        }
        return null;
    }

    public String displayAll() {
        String output = "";
        List<User> varList = daoUser.findAll();
        for (int i = 0; i < varList.size(); i++) {
            output += varList.get(i).toString() + "\n";
        }
        return output;
    }

    public String showRichest() {
        String out = daoUser.findRichest();
        return out;
    }

    public String bankSumm() {
        return daoUser.totalBank();
    }


}
