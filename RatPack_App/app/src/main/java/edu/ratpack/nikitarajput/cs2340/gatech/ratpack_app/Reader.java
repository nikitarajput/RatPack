package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

/**
 * Created by aaron on 10/9/2017.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reader {

    static String line= "";
    static FirebaseAuth mAuth;
    static FirebaseDatabase mFirebaseDatabase;
    static DatabaseReference dbRef;

    public static void main(String[] args) {



        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();

        //int badCount = 0;
        //This needs to be the filePath to the CSV on your computer
        String csvFile = "/Users/aaron/eclipse-workspace/RatPack/src/Res/Rat_Sightings.csv";
        //THIS IS THE ERROR**********************************************************
        String cvsSplitBy = ",";

        int cols = 52;//# of cols in csv
        int[] goodCols = new int[] {1,7,8,9, 16,23, 49, 50};//only cols we care about
        boolean[] mask = new boolean[cols];
        DatabaseReference ratsRef = dbRef.child("rats");

        ArrayList<Rat> allRats = new ArrayList<Rat>();//holding rats

        for(int i = 0; i< cols; i++) {
            for(int j = 0; j < goodCols.length; j++) {
                if(i ==goodCols[j])//sets mask true if is a col we care about
                    mask[i]=true;
            }
        }

        try  {
            BufferedReader buffy = new BufferedReader(new FileReader(csvFile));//give buff the file
            line=buffy.readLine();//skips first line(the headers)
            while ((line = buffy.readLine()) != null) {//stops when null line

                // use comma as separator
                String[] rats = line.split(cvsSplitBy);//only one line at a time
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
                //makes rat object
                //public Rat(String name, double longitude, double latitude, String locationType, String address, String city, int zipCode, String borough) {
                int zip;
                try {
                    zip = Integer.parseInt(goodRats[2]);
                } catch(Exception e){
                    zip = -1;
                }
                double lon;
                try{
                    lon = Double.parseDouble(goodRats[7]);
                } catch (Exception e){
                    lon = -1;
                }
                double lat;
                try{
                    lat = Double.parseDouble(goodRats[6]);
                } catch (Exception e){
                    lat = -1;
                }
                String locationType = goodRats[1];
                String address = goodRats[3];
                String city = goodRats[4];
                String borough = goodRats[5];
                Rat temp = new Rat("No Name(CSV)",lon, lat,locationType,address,city,zip,borough);

                //add rat to Firebase
                DatabaseReference newRatRef = ratsRef.push();

                String ratID = newRatRef.getKey();
                temp.setUniqueKey(parseKeyReader(ratID));
                newRatRef.setValue(temp);

                /*Map<String, Object> map = new HashMap<>();
                map.put("/Users/" + temp.get + "/", null);
                map.put("/Groups/" + groupId + "/Users/" + userId + "/", new HashMap<>().put(userId, null));
                //other locations
                databaseReference.updateChildren(map);*/
                //maybe useful for a single push?
            //}


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
        //System.out.println("\n\nTimes Failed: "+badCount+"\nPercent success: "+((100638.0-badCount)/(100638.0)));


    }
    static boolean checker(String[] strings){
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

    //copied from Rat_Input_activity
    private static String parseKeyReader(String url){
        return url.substring(url.lastIndexOf('-') + 1);
    }

}
