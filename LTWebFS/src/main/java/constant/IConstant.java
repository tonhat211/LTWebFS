package constant;

public class IConstant {
    public static final String FACEBOOK_CLIENT_ID = "1443587996285926";
    public static final String FACEBOOK_CLIENT_SECRET = "9b6cf66895b67e3d3da8bb521b798675";
    public static final String FACEBOOK_REDIRECT_URI = "http://localhost:8080/LTWebFS/loginFB";
    public static final String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/v19.0/oauth/access_token";
    public static final String FACEBOOK_LINK_GET_USER_INFO = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token=";

    public static final String GOOGLE_CLIENT_ID = "922388877466-mefk0ck070diu19f1dmqhpsvefcm08n7.apps.googleusercontent.com";

    public static final String GOOGLE_CLIENT_SECRET = "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";

    public static final String GOOGLE_REDIRECT_URI = "http://localhost:8080/LTWebFS/loginGG";

    public static final String GOOGLE_GRANT_TYPE = "authorization_code";

    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

}
