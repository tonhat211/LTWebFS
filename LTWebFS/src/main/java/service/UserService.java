package service;

import database.UserDAO;
import model.User;

public class UserService {
    public boolean validate(String name, String email, String password, String phone, String address, String info) {
        if (name == null || name.equalsIgnoreCase("")) {
            return false;
        }
        if (email == null || email.equalsIgnoreCase("") || !FormatUtils.validEmail(email)) {
            return false;
        }
        if (password == null || password.equalsIgnoreCase("")) {
            return false;
        }
        if (phone == null || phone.equalsIgnoreCase("")) {
            return false;
        }
        if (address == null || address.equalsIgnoreCase("")) {
            return false;
        }
        if (info == null || info.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
