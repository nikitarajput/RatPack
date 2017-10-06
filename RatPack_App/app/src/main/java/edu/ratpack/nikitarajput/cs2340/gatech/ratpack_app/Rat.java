package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import java.io.Serializable;

/**
 * Created by soniaggarwal on 10/6/17.
 */

public class Rat {

    private String uniqueKey;
    private double longitude;
    private double latitude;
    private String date;
    private String time;
    private LocationType locationType;
    private int zipCode;
    private String address;
    private String city;
    private Borough borough;

    public Rat(double longitude, double latitude, LocationType locationType, int zipCode, String address, String city, Borough borough) {
        // set the unique key from firebase
        this.longitude = longitude;
        this.latitude = latitude;
        // set date
        // set time
        this.locationType = locationType;
        this.zipCode = zipCode;
        this.address = address;
        this.city = city;
        this.borough = borough;
    }

    // do getters and setters here

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
