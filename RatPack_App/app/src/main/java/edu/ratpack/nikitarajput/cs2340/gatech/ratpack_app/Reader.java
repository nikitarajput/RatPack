package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

/**
 * Created by aaron on 10/9/2017.
 */
import android.support.annotation.RequiresPermission;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.FirebaseDatabase.*;


public class Reader {

    static String line= "";
    static Map<String, Object> map = new HashMap<>();
    static ArrayList<Rat> allRats;//holding rats
    //private static FirebaseDatabase db;
    //private static FirebaseAuth mAuth;
    //private static DatabaseReference dbRef;



    public static Map<String, Object> updateMap() {

        //This needs to be the filePath to the CSV on your computer
        String csvFile = "/Users/aaron/eclipse-workspace/RatPack/src/Res/Rat_Sightings.csv";
        //THIS IS THE ERROR**********************************************************

        //int badCount = 0;
        String cvsSplitBy = ",";
        int cols = 52;//# of cols in csv
        int[] goodCols = new int[] {1,7,8,9, 16,23, 49, 50};//only cols we care about
        boolean[] mask = new boolean[cols];
        allRats = new ArrayList<Rat>();//holding rats, but serves no purpose anymore
        map = new HashMap<>();

        //FirebaseApp fbApp = FirebaseApp.initializeApp(5);
        //mAuth = FirebaseAuth.getInstance();
        //db = FirebaseDatabase.getInstance();
        //dbRef = db.getReference();




        for(int i = 0; i< cols; i++) {//makes boolean mask
            for(int j = 0; j < goodCols.length; j++) {
                if(i ==goodCols[j])//sets mask true if is a col we care about
                    mask[i]=true;
            }
        }

        try  {
            BufferedReader buffy = new BufferedReader(new FileReader(csvFile));//give buffy the file
            line=buffy.readLine();//skips first line(the headers)
            int whileCounter = 0;
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
                allRats.add(whileCounter,temp);
                map.put("/rats/"+rats[0],temp);
                Log.d("TEST","ADDED rat ->  #" + whileCounter);
                whileCounter++;

                //used ot check individual rats
                /*if(!Reader.checker(goodRats)){
                    badCount++;
                    for(int i = 0; i < goodRats.length; i++){
                        System.out.print(goodRats[i]+", ");
                    }
                    System.out.println();
                }*/

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
        //Reader.pushToFB();
        //System.out.println("SUCCESS");
        //System.out.println("\n\nTimes Failed: "+badCount+"\nPercent success: "+((100638.0-badCount)/(100638.0)));


    }
    public static Rat makeRat(String[] attr){
        int zip;
        try {
            zip = Integer.parseInt(attr[2]);
        } catch(Exception e){
            zip = -1;
        }
        double lon;
        try{
            lon = Double.parseDouble(attr[7]);
        } catch (Exception e){
            lon = -1;
        }
        double lat;
        try{
            lat = Double.parseDouble(attr[6]);
        } catch (Exception e){
            lat = -1;
        }
        String locationType = attr[1];
        String address = attr[3];
        String city = attr[4];
        String borough = attr[5];
        return new Rat("No Name(CSV)",lon, lat,locationType,address,city,zip,borough);
    }

    public static boolean checker(String[] strings){
        if(!strings[2].equals("Unspecified") && !strings[2].equals("N/A") ){
            try {
                int zip = Integer.parseInt(strings[2]);
            } catch(Exception e){
                return false;
            }
        }
        else if(!strings[6].equals("Unspecified") && !strings[6].equals("N/A")){
            try{
                double lat = Double.parseDouble(strings[6]);
            } catch (Exception e){
                return false;
            }
        }
        else if(!strings[7].equals("Unspecified") && !strings[7].equals("N/A")){
            try{
                double lon = Double.parseDouble(strings[6]);
            } catch (Exception e){
                return false;
            }
        }

        return true;
    }


    private static void pushToFB(){
        //new Rat_Input_Activity().reader(map);
        //dbRef.updateChildren(map);

    }
    //copied from Rat_Input_activity
    private static String parseKeyReader(String url){
        return url.substring(url.lastIndexOf('-') + 1);
    }

}
