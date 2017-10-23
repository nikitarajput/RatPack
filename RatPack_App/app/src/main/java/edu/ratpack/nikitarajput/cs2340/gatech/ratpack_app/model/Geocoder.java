package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Geocoder {
    private static final String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String APIkey = "&key=AIzaSyCif2_-0qjOnioEUSrMWq1t3vFb0UnH4gM";
    private double lat;
    private double lng;

    public Geocoder() {
    }

    public String buildURL(String address, String city) {
        String newURL =  url + address + "," + city + ",NY" + APIkey;
        return newURL;

    }

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

    public double getLat() {
        return lat;
    }

    public double getLong() {
        return lng;
    }
}

