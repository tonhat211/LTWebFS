package database;

public class test {
    public static void main(String[] args) {
        String name = "Tran Thi Thuy";
        String email = "trongtin3152@gmail.com";
        String password = "123";
        String phone = "123";
        String address = "265";
        String info = "nam - 2000";
        UserDAO userDAO = UserDAO.getInstance();
//        boolean user = userDAO.register(name, email, password, phone, address, info);
//        if (user) {
//            System.out.println("Successfully registered");
//        } else {
//            System.out.println("Unsuccessfully registered");
//        }
    }
}


