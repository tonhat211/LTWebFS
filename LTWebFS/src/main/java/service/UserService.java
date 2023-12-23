package service;

import model.User;

public class UserService {

    public boolean checkUser(String email, String password) {
        if (email.equals("trongtin3152@gmail.com") && password.equals("123")) {
            return true;
        }
        return false;
    }
}
