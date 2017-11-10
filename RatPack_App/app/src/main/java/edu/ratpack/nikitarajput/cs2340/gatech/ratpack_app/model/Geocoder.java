package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            JSONArray aTemp = (JSONArray)data.get("results");
            JSONObject oTemp = aTemp.getJSONObject(0);
            JSONObject oTemp1 = oTemp.getJSONObject("geometry");
            JSONObject oTemp2 = oTemp1.getJSONObject("location");

            lat = oTemp2.getDouble("lat");

            lng = oTemp2.getDouble("lng");

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

