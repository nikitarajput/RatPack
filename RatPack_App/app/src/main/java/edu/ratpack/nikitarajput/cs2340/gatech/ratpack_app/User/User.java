package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.User;

/**
 * Created by Shreya on 9/29/17.
 */

public class User {
    private String loginName;
    private String password;
    private boolean isLocked;
    private String email;

    public User(String loginName, String password, boolean isLocked, String email) {
        this.loginName = loginName;
        this.password = password;
        this.isLocked = isLocked;
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }
    public String getPassword() {
        return password;
    }
    public boolean getIsLocked() {
        return isLocked;
    }
    public String getEmail() {
        return email;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
