package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A user is anyone who wants to use the system to view data, or enter new sightings.
 */
public class User {
    private String username;
    private String password;
    private boolean isLocked;
    private boolean isAdmin;

    public User() {

    }

    public User(String username, String password) {
        this(username, password, false, false);
    }

    public User(String username, String password, boolean isAdmin) {
        this(username, password, false, isAdmin);
    }

    public User(String username, String password,boolean isLocked, boolean isAdmin) {
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
