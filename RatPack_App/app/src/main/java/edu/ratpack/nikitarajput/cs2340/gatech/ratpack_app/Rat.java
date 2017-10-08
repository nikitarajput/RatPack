package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.location.Location;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by soniaggarwal on 10/6/17.
 */

public class Rat {

    private String uniqueKey;
    private String name;
    private double longitude;
    private double latitude;
    private String date;
    private String time;
    private LocationType locationType;
    private int zipCode;
    private String address;
    private String city;
    private Borough borough;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;

    public Rat(String name, LocationType locationType, String address, String city, int zipCode, Borough borough) {
        this(name, 0, 0, locationType, address, city, zipCode, borough);
    }

    public Rat(String name, double longitude, double latitude, LocationType locationType, String address, String city, int zipCode, Borough borough) {
        // set the unique key from firebase
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = createDate();
        this.time = createTime();
        this.locationType = locationType;
        this.zipCode = zipCode;
        this.address = address;
        this.city = city;
        this.borough = borough;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();
    }

    /**
     * Creates the current date.
     *
     * @return the current date.
     */
    private String createDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
        return timeFormat.format(calendar.getTime());
    }

    /**
     * Creates the current time.
     *
     * @return the current time.
     */
    private String createTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(calendar.getTime());
    }

    /**
     * Gets the unique key.
     *
     * @return the unique key.
     */
    public String getUniqueKey() {
        return this.uniqueKey;
    }

    /**
     * Sets the unique key.
     *
     * @param uniqueKey the unique key to be set.
     */
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name the the name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the latitude.
     *
     * @return the latitude.
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Sets the latitude.
     *
     * @param latitude the the latitude to be set.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude.
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude the the longitude to be set.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the date.
     *
     * @return the date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Sets the date.
     *
     * @param date the date to be set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time.
     *
     * @return the time.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Sets the time.
     *
     * @param time the time to be set.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the location type.
     *
     * @return the location type.
     */
    public LocationType getLocationType() {
        return this.locationType;
    }

    /**
     * Sets the location type.
     *
     * @param locationType the location type to be set.
     */
    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code.
     */
    public int getZipCode() {
        return this.zipCode;
    }

    /**
     * Sets the zip code.
     *
     * @param zipCode the zip code to be set.
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the address.
     *
     * @return the address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address.
     *
     * @param address the addresss to be set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the city.
     *
     * @return the city.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the city.
     *
     * @param city the city to be set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the borough.
     *
     * @return the borough.
     */
    public Borough getBorough() {
        return this.borough;
    }

    /**
     * Sets the borough.
     *
     * @param borough the borough to be set.
     */
    public void setBorough(Borough borough) {
        this.borough = borough;
    }

    public enum LocationType implements Serializable {
        FAMILY_DWELLING("1-2 Family Dwelling"),
        FAMILY_APARTMENT("3+ Family Apartment Building"),
        FAMILY_MIXED_USE("3+ Family Mixed Use Building"),
        COMMERCIAL("Commercial Building"),
        VACANT("Vacant Lot"),
        CONSTRUCTION("Construction Site"),
        HOSPITAL("Hospital"),
        SEWER("Catch Basin/Sewer");

        private String name;

        /**
         * Constructor for the enumeration
         *
         * @param name name for the location type.
         */
        LocationType(String name) { this.name = name; }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum Borough implements Serializable {
        MANHATTAN("Manhattan"),
        STATEN_ISLAND("Staten Island"),
        QUEENS("Queens"),
        BROOKLYN("Brooklyn"),
        BRONX("Bronx");

        private String name;

        /**
         * Constructor for the enumeration
         *
         * @param name name for the borough.
         */
        Borough(String name) { this.name = name; }

        @Override
        public String toString() {
            return name;
        }
    }
}
