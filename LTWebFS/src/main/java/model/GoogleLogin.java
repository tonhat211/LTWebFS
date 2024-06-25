package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import constant.IConstant;

public class GoogleLogin {
    public static String getToken(String code) throws ClientProtocolException, IOException {

        String response = Request.Post(IConstant.GOOGLE_LINK_GET_TOKEN)

                .bodyForm(

                        Form.form()

                                .add("client_id", IConstant.GOOGLE_CLIENT_ID)

                                .add("client_secret", IConstant.GOOGLE_CLIENT_SECRET)

                                .add("redirect_uri", IConstant.GOOGLE_REDIRECT_URI)

                                .add("code", code)

                                .add("grant_type", IConstant.GOOGLE_GRANT_TYPE)

                                .build()

                )

                .execute().returnContent().asString();



        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

        return accessToken;

    }

}
