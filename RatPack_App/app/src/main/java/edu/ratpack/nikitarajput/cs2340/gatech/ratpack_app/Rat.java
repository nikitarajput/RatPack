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
    private long longitude;
    private long latitude;
    private String date;
    private String time;
    private LocationType locationType;
    private long zipCode;
    private String address;
    private String city;
    private Borough borough;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;

    public Rat(String name, LocationType locationType, String address, String city, long zipCode, Borough borough) {
        this(name, 0, 0, locationType, address, city, zipCode, borough);

    }

    public Rat(String name, long longitude, long latitude, LocationType locationType, String address, String city, long zipCode, Borough borough) {
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
        Log.d("TEST R", "init list to length: "+rats.length);
        Iterator<DataSnapshot> list = data.getChildren().iterator();//think this is doesn't give rats
        //HashMap<String, Rat> temp = list.next().getValue(Rat.class);
        int i = 0;
        //has to get individual values. Enums are stored as lowercase so throws error if don't String.toUpperCase()
        for (DataSnapshot messageSnapshot: data.getChildren()) {
            String name = (String)messageSnapshot.child("name").getValue();
            long longitude = (long)messageSnapshot.child("longitude").getValue();
            long latitude = (long)messageSnapshot.child("latitude").getValue();
            String date = (String)messageSnapshot.child("date").getValue();
            String time = (String)messageSnapshot.child("time").getValue();
            LocationType locationType = LocationType.valueOf(((String)messageSnapshot.child("locationType").getValue()).toUpperCase());
            long zipCode = (long)messageSnapshot.child("zipCode").getValue();
            String address =(String)messageSnapshot.child("address").getValue();
            String city = (String)messageSnapshot.child("city").getValue();
            Borough borough = Borough.valueOf(((String)messageSnapshot.child("borough").getValue()).toUpperCase());
            rats[i] = new Rat( name, longitude, latitude, locationType, address, city, zipCode, borough);
            i++;
        }

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
    public long getLatitude() {
        return this.latitude;
    }

    /**
     * Sets the latitude.
     *
     * @param latitude the the latitude to be set.
     */
    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude.
     */
    public long getLongitude() {
        return this.longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude the the longitude to be set.
     */
    public void setLongitude(long longitude) {
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
    public long getZipCode() {
        return this.zipCode;
    }

    /**
     * Sets the zip code.
     *
     * @param zipCode the zip code to be set.
     */
    public void setZipCode(long zipCode) {
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
