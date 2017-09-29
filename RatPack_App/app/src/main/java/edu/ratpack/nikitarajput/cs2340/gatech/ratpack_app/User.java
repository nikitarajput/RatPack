package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

/**
 * A user is anyone who wants to use the system to view data, or enter new sightings.
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

    /**
     * Getter for login name
     * @return loginName the login name of the user, which is the user's email
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Getter for password
     * @return password the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for isLocked
     * @return isLocked whether or not the account is locked
     */
    public boolean getIsLocked() {
        return isLocked;
    }

    /**
     * Getter for email
     * @return email the email of sthe user, which is also the user's login name
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for login name
     * @param loginName the login name of the user, which is the user's email
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * Setter for password
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for isLocked
     * @param isLocked whether or not the account is locked
     */
    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Setter for email
     * @param email the email of the user, which is also the user's login name
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
