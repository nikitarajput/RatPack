package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Reader;

import static org.junit.Assert.*;


/**
 * Created by aaron on 11/7/17.
 */

public class makeRatTester {
    @Parameterized.Parameter
    static Rat test;
    @Parameterized.Parameter
    static String[] input;

    @Test
    public void invalid_nums() throws Exception {
        test = new Rat("No Name(CSV)", -1, -1, "locationType", "address", "city", -1, "borough");
        input = new String[]{"unimportant", "locationType","Zip", "address", "city", "borough", "Longitude", "latitude"};
        assertEquals(test.getAddress(), Reader.makeRat(input).getAddress());
        assertEquals(test.getLongitude(), Reader.makeRat(input).getLongitude(), 0.000000000001);
        assertEquals(test.getLatitude(), Reader.makeRat(input).getLatitude(), 0.0000000000001);
        assertEquals(test.getZipCode(), Reader.makeRat(input).getZipCode());
        assertEquals(test.getName(), Reader.makeRat(input).getName());
        assertEquals(test.getLocationType(), Reader.makeRat(input).getLocationType());
        assertEquals(test.getCity(), Reader.makeRat(input).getCity());
        assertEquals(test.getBorough(), Reader.makeRat(input).getBorough());
    }
    @Test
    public void large_nums() throws Exception {
        test = new Rat("No Name(CSV)", -1, -1, "locationType", "address", "city", -1, "borough");
        input = new String[]{"unimportant", "locationType",
                "54354354354354354354354354354354354354543543543543543543543543543543543543543545435435435435435435435435435435435435435435435435435",
                "address", "city", "borough",
                "5435435435435435435435435435435435435454354354354354354354354354354354354354354543543543543543543543543543543543543543543543543543554" +
                        "35435435435435435435435435435435435454354354354354354354354354354354354354354543543543543543543543543543543543543543543543543" +
                        "543554354354354354354354354354354354354354543543543543543543543543543543543543543545435435435435435435435435435435435435435435435435435",
                "-543543543543543543543543543543543543545435435435435435435435435435435435435435454354354354354354354354354354354354354354354354354355435" +
                        "435435435435435435435435435435435454354354354354354354354354354354354354354543543543543543543543543543543543543543543543543543" +
                        "554354354354354354354354354354354354354543543543543543543543543543543543543543545435435435435435435435435435435435435435435435435435"};
        assertEquals(test.getAddress(), Reader.makeRat(input).getAddress());
        assertEquals(test.getLongitude(), Reader.makeRat(input).getLongitude(), 0.000000000001);
        assertEquals(test.getLatitude(), Reader.makeRat(input).getLatitude(), 0.0000000000001);
        assertEquals(test.getZipCode(), Reader.makeRat(input).getZipCode());
        assertEquals(test.getName(), Reader.makeRat(input).getName());
        assertEquals(test.getLocationType(), Reader.makeRat(input).getLocationType());
        assertEquals(test.getCity(), Reader.makeRat(input).getCity());
        assertEquals(test.getBorough(), Reader.makeRat(input).getBorough());


    }
    @Test
    public void null_input() throws Exception {
        test = new Rat("No Name(CSV)", -1, -1, "N/A", "N/A", "N/A", -1, "N/A");
        input = null;
        assertEquals(test.getAddress(), Reader.makeRat(input).getAddress());
        assertEquals(test.getLongitude(), Reader.makeRat(input).getLongitude(), 0.000000000001);
        assertEquals(test.getLatitude(), Reader.makeRat(input).getLatitude(), 0.0000000000001);
        assertEquals(test.getZipCode(), Reader.makeRat(input).getZipCode());
        assertEquals(test.getName(), Reader.makeRat(input).getName());
        assertEquals(test.getLocationType(), Reader.makeRat(input).getLocationType());
        assertEquals(test.getCity(), Reader.makeRat(input).getCity());
        assertEquals(test.getBorough(), Reader.makeRat(input).getBorough());
    }
    @Test
    public void normal() throws Exception {
        test = new Rat("No Name(CSV)", 68.765454545, 33.6565656, "hospital", "2886 Northbrook Dr", "Atlanta", 30344, "Bronx");
        input = new String[]{"unimportant", "hospital","30344", "2886 Northbrook Dr", "Atlanta", "Bronx", "33.6565656", "68.765454545"};
        assertEquals(test.getAddress(), Reader.makeRat(input).getAddress());
        assertEquals(test.getLongitude(), Reader.makeRat(input).getLongitude(), 0.000000000001);
        assertEquals(test.getLatitude(), Reader.makeRat(input).getLatitude(), 0.0000000000001);
        assertEquals(test.getZipCode(), Reader.makeRat(input).getZipCode());
        assertEquals(test.getName(), Reader.makeRat(input).getName());
        assertEquals(test.getLocationType(), Reader.makeRat(input).getLocationType());
        assertEquals(test.getCity(), Reader.makeRat(input).getCity());
        assertEquals(test.getBorough(), Reader.makeRat(input).getBorough());
    }

    @Test
    public void incomplete_inputs() throws Exception {
        test = new Rat("No Name(CSV)", -1, -1, "hospital", "2886 Northbrook Dr", "Atlanta", 30344, "N/A");
        input = new String[]{"unimportant", "hospital","30344", "2886 Northbrook Dr", "Atlanta"};
        assertEquals(test.getAddress(), Reader.makeRat(input).getAddress());
        assertEquals(test.getLongitude(), Reader.makeRat(input).getLongitude(), 0.000000000001);
        assertEquals(test.getLatitude(), Reader.makeRat(input).getLatitude(), 0.0000000000001);
        assertEquals(test.getZipCode(), Reader.makeRat(input).getZipCode());
        assertEquals(test.getName(), Reader.makeRat(input).getName());
        assertEquals(test.getLocationType(), Reader.makeRat(input).getLocationType());
        assertEquals(test.getCity(), Reader.makeRat(input).getCity());
        assertEquals(test.getBorough(), Reader.makeRat(input).getBorough());
    }

    @BeforeClass
    public static void build(){
        test = new Rat();
        input = new String[8];
    }


}
