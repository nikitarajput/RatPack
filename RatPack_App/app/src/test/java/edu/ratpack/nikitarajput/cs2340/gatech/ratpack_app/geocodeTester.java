package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runners.Parameterized;
import java.io.FileInputStream;
import java.io.IOException;
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
    public void testLatitude() throws JSONException {
        test = new Geocoder();
        String json = readJSONData("app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest1.json");
        JSONObject blacktap = new JSONObject(json);
        test.parseData(blacktap);
        assertEquals(40.7238189, test.getLat(),0);

        json = readJSONData("app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest2.json");
        JSONObject ratIsland = new JSONObject(json);
        test.parseData(ratIsland);
        assertEquals(40.8551994, test.getLat(),0);

        json = readJSONData("app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest3.json");
        JSONObject trumpTower = new JSONObject(json);
        test.parseData(trumpTower);
        assertEquals(40.7623737, test.getLat(),0);
    }

    @Test
    public void testLongitude() throws Exception {
        test = new Geocoder();
        String json = readJSONData("app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest1.json");
        JSONObject blacktap = new JSONObject(json);
        test.parseData(blacktap);
        assertEquals(-74.0043125, test.getLong(),0);

        json = readJSONData("app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest2.json");
        JSONObject ratIsland = new JSONObject(json);
        test.parseData(ratIsland);
        assertEquals(-73.78097199999999, test.getLong(),0);

        json = readJSONData("app/src/test/java/edu/ratpack/nikitarajput/cs2340/gatech/ratpack_app/resources/jsonTest3.json");
        JSONObject trumpTower = new JSONObject(json);
        test.parseData(trumpTower);
        assertEquals(-73.97391189999999, test.getLong(),0);
    }
}
