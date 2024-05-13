package model;

import java.sql.Timestamp;

public class VerifyCode {
	private String code;
    private String email;
    private Timestamp time;
    private int isVerify;

    public VerifyCode(String code, String email, Timestamp time, int isVerify) {
        this.code = code;
        this.email = email;
        this.time = time;
        this.isVerify = isVerify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VerifyCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(int isVerify) {
        this.isVerify = isVerify;
    }

    @Override
    public String toString() {
        return "VerifyCode{" +
                "code='" + code + '\'' +
                ", time=" + time +
                ", isVerify=" + isVerify +
                '}';
    }


}
