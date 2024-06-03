package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.IConstant;
import database.UserDAO;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class FacebookAccount {
    public User getUserInfo(String accessToken) throws IOException {
        String link = IConstant.FACEBOOK_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        Datee dateinDatee = Datee.getToday();

        int idin = UserDAO.getInstance().selectTheMaxID() +1;
        String name = jobj.get("name").getAsString();

        User user = new User(idin, name,0, dateinDatee, 0);

        return user;
    }
}
