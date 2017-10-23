package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nikitarajput on 10/22/17.
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
        String newURL =  url + address + "," + city + ",NY" + APIkey;
        return newURL;

    }

    /**
     * retrieves location latitude and longitude from JSON object from geocoding API call
     * @param data JSON object from geocoding API call
     */
    public void parseData(JSONObject data) {
        try {
            lat = ((JSONArray)data.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");

            lng = ((JSONArray)data.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lng");

            Log.d("latitude", "" + lat);
            Log.d("longitude", "" + lng);

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

