package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A user is anyone who wants to use the system to view data, or enter new sightings.
 */
public class User implements Parcelable {
    private String loginName;
    private String password;
    private boolean isLocked;
    private String email;
    //ArrayList<String> allNames=new ArrayList<String>();
    //ArrayList<String> allPasses = new ArrayList<String>();


    public User(String loginName, String password, boolean isLocked, String email) {
        this.loginName = loginName;
        this.password = password;
        this.isLocked = isLocked;
        this.email = email;

    }

    /**
     * Constructor used for Parcel
     * @param in the
     *
     */
    private User(Parcel in) {
        loginName = in.readString();
        password = in.readString();
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

    /**
     * Method from Parcelable
     */
    public int describeContents() {
        return 0;
    }

    /**
     * Method from Parcelable, Writes loginName and password ot parcel
     * @param dest the destination of the parcel file (I think)
     * @param flags any flags concerning the parcel
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loginName);
        dest.writeString(password);
}

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
