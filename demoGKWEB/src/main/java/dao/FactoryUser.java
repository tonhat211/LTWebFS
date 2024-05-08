package dao;

import model.UserModel;

public class FactoryUser {
    private static FactoryUser instance = new FactoryUser();

    public static FactoryUser getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return (UserDAO) new UserModel();
    }
}
