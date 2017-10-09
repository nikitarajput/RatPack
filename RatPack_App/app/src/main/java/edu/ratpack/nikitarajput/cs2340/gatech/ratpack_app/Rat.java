package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.location.Location;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by soniaggarwal on 10/6/17.
 */

public class Rat {
    private static Rat[] rats=new Rat[0];
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


    public Rat(String name, String locationType, String address, String city, int zipCode, String borough) {
        this(name, 0, 0, locationType, address, city, zipCode, borough);

    }


    public Rat(String name, double longitude, double latitude, String locationType, String address, String city, int zipCode, String borough) {
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

    }
    public Rat() {}//just used so we can avoid static methods

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


    //doesn't work. Returns null rat array
    public static Rat[] updateList(){
        DatabaseReference dbTemp = FirebaseDatabase.getInstance().getReference().child("rats");
        //only way i could find to get data from fireBase
        dbTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                makeList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return rats;
    }

    /**
     * Helper method to @method updateList() to get Rat[] from the ValueEventListener
     * @param data the snapshot of the current rat data
     * @return current list of rats in the FirebaseDatabase
     */
    private static Rat[] makeList(DataSnapshot data){
        rats = new Rat[(int)data.getChildrenCount()];
        Log.d("TEST", "Size of array is: "+rats.length);
        int i = 0;
        for (DataSnapshot snap: data.getChildren()) {
            /*String name = (String)snap.child("name").getValue();
            double longitude = (long)snap.child("longitude").getValue();
            double latitude = (long)snap.child("latitude").getValue();
            String date = (String)snap.child("date").getValue();
            String time = (String)snap.child("time").getValue();
            String locationType = (String)snap.child("locationType").getValue();
            Integer zc = (int) (long)snap.child("zipCode").getValue();
            int zipCode =zc.intValue();
            String address = (String)snap.child("address").getValue();
            String city = (String)snap.child("city").getValue();
            String borough = (String)snap.child("borough").getValue();
            rats[i] = new Rat(name, longitude, latitude, locationType, address, city, zipCode, borough);*/
            rats[i] = snap.getValue(Rat.class);
            i++;
        }
        Log.d("TEST isNull", "Rat[0] name: "+(rats[0]==null? "null":rats[0].getName()));
        return rats;
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
