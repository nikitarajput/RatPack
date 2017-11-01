package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

/**
 * Created by aaron on 10/9/2017.
 */
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;



public class Reader {


    static String line= "";
    static Map<String, Object> map = new HashMap<>();



    /**
     * Creates a map from the given csv file containing all the rats in the file
     * @param ctx is the context from the class calling this method, allows creation of AssetManager
     */
    public static Map<String, Object> updateMap(Context ctx) {

        //This needs to be the file name of the CSV in assets
        AssetManager assMan = ctx.getAssets();
        String csvFile = "Rat_Sightings_12485.csv";

        String cvsSplitBy = ",";
        int cols = 52;//# of cols in csv
        int[] goodCols = new int[] {1,7,8,9, 16,23, 49, 50};//only cols we care about
        boolean[] mask = new boolean[cols];
        map = new HashMap<>();

        for(int i = 0; i< cols; i++) {//makes boolean mask
            for(int j = 0; j < goodCols.length; j++) {
                if(i ==goodCols[j])//sets mask true if is a col we care about
                    mask[i]=true;
            }
        }
        Log.d("TEST", "in Reader.updateMap, going into try statement");

        try {
            Log.d("TEST","Inside Try Statement, trying to open file");
            //InputStreamReader test = new InputStreamReader(assMan.open(csvFile));
            Log.d("TEST","Opened file");

            BufferedReader buffy = new BufferedReader(new InputStreamReader(assMan.open(csvFile)));//give buffy the file
            Log.d("TEST","File read in properly");
            line=buffy.readLine();//skips first line(the headers)
            if(line.trim().length()==0)
                line=buffy.readLine();//skips another line if first line is blank
            int whileCounter = 0;
            Log.d("TEST","Entering While loop");

            while ((line = buffy.readLine()) != null) {//loops until finds the end of CSV

                String[] rats = line.split(cvsSplitBy);//every value in a row
                String[] goodRats =new String[goodCols.length];//only values we care about
                int j=0;

                for(int i = 0; i < cols; i++) {//sets up goodRats
                    if(mask[i]) {
                        try {
                            goodRats[j] = (rats[i]!=null && rats[i].trim().length()> 0? rats[i]:"Unspecified");
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
                if(whileCounter < 3000){
                    //defaults to today's date
                } else if(whileCounter < 6000){
                    temp.setDate("Jun 6, 2016");
                } else if(whileCounter < 9000){
                    temp.setDate("May 5, 2015");
                }else if(whileCounter < 12000){
                    temp.setDate("Apr 4, 2014");
                } else{
                    temp.setDate("Oct 10, 2017");
                }//*/
                map.put("zz"+rats[0],temp);
                Log.d("TEST","ADDED rat ->  #" + whileCounter);
                whileCounter++;

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
     * @param attr is the list of attributs in the order gotten from the csv from first row to last
     */
    public static Rat makeRat(String[] attr){
        int zip;
        try {
            zip = Integer.parseInt(attr[2]);
        } catch(Exception e){
            zip = -1;
        }
        double lon;
        try{
            lon = Double.parseDouble(attr[6]);
        } catch (Exception e){
            lon = -1;
        }
        double lat;
        try{
            lat = Double.parseDouble(attr[7]);
        } catch (Exception e){
            lat = -1;
        }
        String locationType = attr[1];
        String address = attr[3];
        String city = attr[4];
        String borough = attr[5];
        return new Rat("No Name(CSV)",lon, lat,locationType,address,city,zip,borough);
    }

}
