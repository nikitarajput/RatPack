package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Context;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Geocoder;

import static org.junit.Assert.*;


/**
 * Created by nikitarajput on 11/12/17.
 */

public class geocodeTester {
    @Parameterized.Parameter
    static Geocoder test;
    @Parameterized.Parameter
    static String[] locationInput;
    @Parameterized.Parameter
    static Context myContext;

    @Test
    public void properURL() throws Exception {
        test = new Geocoder();
        locationInput = new String[]{"529 Broome St", "New York"};
        assertEquals("https://maps.googleapis.com/maps/api/geocode/json?address=529 Broome St,New York,NY&key=AIzaSyCif2_-0qjOnioEUSrMWq1t3vFb0UnH4gM", test.buildURL(locationInput[0], locationInput[1]));

        locationInput = new String[]{"Rat Island", "NYC"};
        assertEquals("https://maps.googleapis.com/maps/api/geocode/json?address=Rat Island,NYC,NY&key=AIzaSyCif2_-0qjOnioEUSrMWq1t3vFb0UnH4gM", test.buildURL(locationInput[0], locationInput[1]));

        locationInput = new String[]{"725 5th Ave", "New York"};
        assertEquals("https://maps.googleapis.com/maps/api/geocode/json?address=725 5th Ave,New York,NY&key=AIzaSyCif2_-0qjOnioEUSrMWq1t3vFb0UnH4gM", test.buildURL(locationInput[0], locationInput[1]));
    }

    public String readJSONData(String filename) {
        String json = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            json = IOUtils.toString(fis, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    @Test
    public void testLatitude() throws Exception {
        test = new Geocoder();
        String json = readJSONData("/Users/nikitarajput/Desktop/CS2340/RatPack/RatPack_App/app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest1.json");
        JSONObject blacktap = new JSONObject(json);
        test.parseData(blacktap);
        assertEquals(40.7238189, test.getLat(),0);

        json = readJSONData("/Users/nikitarajput/Desktop/CS2340/RatPack/RatPack_App/app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest2.json");
        JSONObject ratIsland = new JSONObject(json);
        test.parseData(ratIsland);
        assertEquals(40.8551994, test.getLat(),0);

        json = readJSONData("/Users/nikitarajput/Desktop/CS2340/RatPack/RatPack_App/app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest3.json");
        JSONObject trumpTower = new JSONObject(json);
        test.parseData(trumpTower);
        assertEquals(40.7623737, test.getLat(),0);
    }

    @Test
    public void testLongitude() throws Exception {
        test = new Geocoder();
        String json = readJSONData("/Users/nikitarajput/Desktop/CS2340/RatPack/RatPack_App/app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest1.json");
        JSONObject blacktap = new JSONObject(json);
        test.parseData(blacktap);
        assertEquals(-74.0043125, test.getLong(),0);

        json = readJSONData("/Users/nikitarajput/Desktop/CS2340/RatPack/RatPack_App/app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest2.json");
        JSONObject ratIsland = new JSONObject(json);
        test.parseData(ratIsland);
        assertEquals(-73.78097199999999, test.getLong(),0);

        json = readJSONData("/Users/nikitarajput/Desktop/CS2340/RatPack/RatPack_App/app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest3.json");
        JSONObject trumpTower = new JSONObject(json);
        test.parseData(trumpTower);
        assertEquals(-73.97391189999999, test.getLong(),0);
    }




}
