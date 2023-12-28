package database;

import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws SQLException {
        String email = "18130244@st.hcmuaf.edu.vn";
        UserDAO userDAO = new UserDAO();
//        ArrayList<User> users = userDAO.findAdmin();
//        for (User user : users) {
//            System.out.println(user.getEmail() + " " + user.getLevel());
//        }
        User user = userDAO.getUserByEmail(email);
        System.out.println(user.getLevel());
    }

}

