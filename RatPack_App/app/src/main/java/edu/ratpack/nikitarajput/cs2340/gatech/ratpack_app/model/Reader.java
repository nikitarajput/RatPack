package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * Helper class to read in mass date from CSV files
 */
public class Reader {


    private static Map<String, Object> map = new HashMap<>();



    /**
     * Creates a map from the given csv file containing all the rats in the file
     * @param ctx is the context from the class calling this method, allows creation of AssetManager
     * @return a map representing all of the Rats from the CSV file.
     */
    public static Map<String, Object> updateMap(Context ctx) {

        //This needs to be the file name of the CSV in assets
        AssetManager assMan = ctx.getAssets();
        String csvFile = "Rat_Sightings_12485.csv";
        String line;
        String cvsSplitBy = ",";
        final int cols = 52;//# of cols in csv
        final int name = 1;
        final int locType = 7;
        final int zip = 8;
        final int address = 9;
        final int city = 16;
        final int borough = 23;
        final int lat = 49;
        final int lon = 50;
        int[] goodCols = new int[] {name,locType,zip,address, city,borough, lat, lon};
        boolean[] mask = new boolean[cols];
        map = new HashMap<>();

        for(int i = 0; i< cols; i++) {//makes boolean mask
            for (int goodCol : goodCols) {
                if (i == goodCol) {//sets mask true if is a col we care about
                    mask[i] = true;
                }

            }
        }
        Log.d("TEST", "in Reader.updateMap, going into try statement");

        try {
            Log.d("TEST","Inside Try Statement, trying to open file");
            //InputStreamReader test = new InputStreamReader(assMan.open(csvFile));
            Log.d("TEST","Opened file");

            InputStreamReader iSR = new InputStreamReader(assMan.open(csvFile));
            BufferedReader buffy = new BufferedReader(iSR);//give buffy the file
            Log.d("TEST","File read in properly");
            line=buffy.readLine();//skips first line(the headers)
            line = line.trim();
            if(line.isEmpty()) {
                line = buffy.readLine();
            }
            int whileCounter = 0;
            Log.d("TEST","Entering While loop");

            line = line.charAt(0) + buffy.readLine();
            line = line.substring(1);
            while (line != null) {//loops until finds the end of CSV

                String[] rats = line.split(cvsSplitBy);//every value in a row
                String[] goodRats =new String[goodCols.length];//only values we care about
                int j=0;

                for(int i = 0; i < cols; i++) {//sets up goodRats
                    if(mask[i]) {
                        try {
                            String currentValue = rats[i];
                            currentValue = currentValue.trim();
                            goodRats[j] = (!currentValue.isEmpty() ? rats[i] : "Unspecified");
                        } catch(Exception e) {
                            goodRats[j] = "Unspecified";
                        }
                        j++;
                    }
                }

                Rat temp = Reader.makeRat(goodRats);
                temp.setUniqueKey(rats[0]);
                /*
                This sets the dates for the different groups of rats.
                Leave the section blank to set it to today's date.
                Remember that any Rats not encompassed by the ranges will be set to today's date.
                Delete the closing comment line below this to comment this out.
                 */
                final int groupSize = 3000;
                if((whileCounter < (2 * groupSize))
                        && (whileCounter >= groupSize)){
                    temp.setDate("Jun 6, 2016");
                } else if(whileCounter < (3 * groupSize)){
                    temp.setDate("May 5, 2015");
                }else if(whileCounter < (4 * groupSize)){
                    temp.setDate("Apr 4, 2014");
                } else{
                    temp.setDate("Oct 10, 2017");
                }//*/
                map.put("zz"+rats[0],null);
                Log.d("TEST","ADDED rat ->  #" + whileCounter);
                whileCounter++;
                line = buffy.readLine();

            }
            Log.d("TEST","Finished While loop");

        } catch (IOException e) {
            Log.d("TEST","Entered catch, oops");
            e.printStackTrace();
        }

        return map;

    }

    /**
     * Creates a Rat from a list of attributes as Strings
     * @param attr is the list of attributes in the order gotten from the csv from first row to last
     * @return A Rat with the given attributes in the array.
     */
    public static Rat makeRat(String[] attr){
        int zip;
        String locationType;
        String address;
        String city;
        String borough;
        try {
            zip = Integer.parseInt(attr[2]);
        } catch(Exception e){
            zip = -1;
        }
        double lon;
        try{
            lon = Double.parseDouble(attr[6]);
            lon = ((lon == Double.POSITIVE_INFINITY)
                    || (lon == Double.NEGATIVE_INFINITY)) ? -1 : lon;
        } catch (Exception e){
            lon = -1;
        }
        double lat;
        try{
            lat = Double.parseDouble(attr[7]);
            lat = ((lat == Double.POSITIVE_INFINITY)
                    || (lat == Double.NEGATIVE_INFINITY)) ? -1 : lat;
        } catch (Exception e){
            lat = -1;
        }
        try {
            locationType = attr[1];
        } catch(Exception e){
            locationType = "N/A";
        }
        try {
            address = attr[3];
        } catch(Exception e){
            address = "N/A";
        }
        try {
            city = attr[4];
        } catch(Exception e){
            city = "N/A";
        }
        try {
            borough = attr[5];
        } catch(Exception e){
            borough = "N/A";
        }
        return new Rat("No Name(CSV)",lat, lon,locationType,address,city,zip,borough);
    }

}
