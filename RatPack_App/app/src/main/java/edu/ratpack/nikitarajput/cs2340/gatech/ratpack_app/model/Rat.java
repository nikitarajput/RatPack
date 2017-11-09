package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Class that handles information for Rat object.
 */
public class Rat {
    // private static final Rat[] rats=new Rat[0];
    private String uniqueKey;
    private String name;
    private double longitude;
    private double latitude;
    private String date;
    private String time;
    private String locationType;
    private int zipCode;
    private String address;
    private String city;
    private String borough;


    /**
     * Rat constructor with longitude and latitude of 0
     * @param name rat name
     * @param locationType incident location type
     * @param address incident address
     * @param city incident city
     * @param zipCode incident zip code
     * @param borough incident borough
     */
    public Rat(String name, String locationType, String address,
               String city, int zipCode, String borough) {
        this(name, 0, 0, locationType, address, city, zipCode, borough);

    }


    /**
     * Rat default constructor
     * @param name rat name
     * @param locationType incident location type
     * @param address incident address
     * @param city incident city
     * @param zipCode incident zip code
     * @param borough incident borough
     * @param latitude incident latitude
     * @param longitude incident longitude
     */
    public Rat(String name, double latitude, double longitude, String locationType,
               String address, String city, int zipCode, String borough) {
        // set the unique key from Firebase
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = createDate();
        this.time = createTime();
        this.locationType = locationType;
        this.zipCode = zipCode;
        this.address = address;
        this.city = city;
        this.borough = borough;

    }

    /**
     * default rat constructor to reference variables in the Rat class
     */
    public Rat() {}

    /**
     * Creates the current date.
     *
     * @return the current date.
     */
    private String createDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        return timeFormat.format(calendar.getTime());
    }

    /**
     * Creates the current time.
     *
     * @return the current time.
     */
    private String createTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
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
    public String getLocationType() {
        return this.locationType;
    }

    /**
     * Sets the location type.
     *
     * @param locationType the location type to be set.
     */
    public void setLocationType(String locationType) {
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
     * @param address the address to be set.
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
    public String getBorough() {
        return this.borough;
    }

    /**
     * Sets the borough.
     *
     * @param borough the borough to be set.
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }

}
