package service;

public class LoginService {
    public boolean validate(String name, String email, String password, String phone, String address, String info) {
        if (name == null || name.equalsIgnoreCase("")) {
            return true;
        }
        if (email == null || email.equalsIgnoreCase("")) {
            return true;
        }
        if (password == null || password.equalsIgnoreCase("")) {
            return true;
        }
        if (phone == null || phone.equalsIgnoreCase("")) {
            return true;
        }
        if (address == null || address.equalsIgnoreCase("")) {
            return true;
        }
        if (info == null || info.equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }
}
