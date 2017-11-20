package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Class used for location services.
 */

public class Geocoder {
    private static final String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String APIkey = "&key=AIzaSyCif2_-0qjOnioEUSrMWq1t3vFb0UnH4gM";
    private double lat;
    private double lng;

    /**
     * creates a Geocoder object
     */
    public Geocoder() {
    }

    /**
     * creates a url used to make an API call to Google Maps's geocoding service
     * @param address rat's address
     * @param city rat's city
     * @return formatted url with passed information
     */
    public String buildURL(String address, String city) {
        return url + address + "," + city + ",NY" + APIkey;
    }

    /**
     * retrieves location latitude and longitude from JSON object from geocoding API call
     * @param data JSON object from geocoding API call
     */
    public void parseData(JSONObject data) {
        try {
            JSONArray results = (JSONArray)data.get("results");
            JSONObject main = results.getJSONObject(0);
            String address = main.getString("formatted_address");
            if (validateCountry(address)) {
                if (validateCity(address)) {
                    JSONObject geometry = main.getJSONObject("geometry");
                    JSONObject location = geometry.getJSONObject("location");
                    if (validateLatLong(location.getDouble("lat"), location.getDouble("lng"))) {
                        lat = location.getDouble("lat");
                        lng = location.getDouble("lng");
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateLatLong(double lat, double lng) {
        boolean checkLatLong = false;
        try {
            if (lat > -85.05115 && lat <= 85) {
                checkLatLong = true;
            } else {
                checkLatLong = false;
                throw new Exception("Latitude out of bounds.");
            }
            if (lng > -180 && lng <= 180) {
                checkLatLong = true;
            } else {
                checkLatLong = false;
                throw new Exception("Longitude out of bounds.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkLatLong;
    }

    private boolean validateCountry(String address) {
        boolean checkCountry = false;
        try {
            if (address.contains("USA")) {
                checkCountry = true;
            } else {
                throw new Exception("Country out of bounds.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkCountry;
    }

    private boolean validateCity(String address) {
        boolean checkCity = false;
        try {
            if (address.contains("NY")) {
                checkCity = true;
            } else {
                throw new Exception("City out of bounds.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkCity;
    }

    /**
     * sets the latitude.
     * @param lat the latitude to be set.
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * sets the longitude.
     * @param lng the longitude to be set.
     */
    public void setLong(double lng) {
        this.lng = lng;
    }

    /**
     * returns location latitude
     * @return latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * returns location longitude
     * @return longitude
     */
    public double getLong() {
        return lng;
    }
}

