package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

/**
 * A user is anyone who wants to use the system to view data, or enter new sightings.
 */
public class User {
    private String username;
    private String password;
    private boolean isLocked;
    private boolean isAdmin;

    /**
     * Creates user object.
     * @param username user's username
     * @param password user's password
     * @param isAdmin user's admins status
     */
    public User(String username, String password, boolean isAdmin) {
        this(username, password, false, isAdmin);
    }

    /**
     * Creates user object.
     * @param username user's username
     * @param password user's password
     * @param isLocked whether or not user's account is locked
     * @param isAdmin user's admins status
     */
    public User(String username, String password, boolean isLocked, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isLocked = isLocked;
        this.isAdmin = isAdmin;

    }

    /**
     * Getter for user name
     *
     * @return username the login name of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for login name
     *
     * @param username the login name of the user, which is the user's email
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password
     *
     * @return password the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for isAdmin
     *
     * @return isAdmin whether or not user is an admin
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Setter for isAdmin
     *
     * @param isAdmin whether or not user is an admin
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Getter for isLocked
     *
     * @return isLocked whether or not the account is locked
     */
    public boolean getIsLocked() {
        return isLocked;
    }

    /**
     * Setter for isLocked
     *
     * @param isLocked whether or not the account is locked
     */
    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
}
