package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

/**
 * Created by aaron on 10/13/17.
 */
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class RatFB {
    private static FirebaseDatabase fbDB;
    private static DatabaseReference dbRef;
    private static  Map<String, Object> masterMap;
    private static Rat[] allRats;
    private static Boolean firstCall;

    public static void init(){
        Log.d("TEST", "Called constructor for RatFB");
        fbDB = FirebaseDatabase.getInstance();
        dbRef = fbDB.getReference();
        masterMap = new HashMap<String, Object>();
        allRats = new Rat[0];
        firstCall = true;
        Log.d("TEST","initialized all variables");
        RatFB.addListener();
    }

    /**
     * Adds a rat to the database and gives it a unique ID
     * @param r rat to be added to database
     */
    public static void addRat(Rat r){
        DatabaseReference ratRef = dbRef.child("rats").push();//makes a spot for the rat
        String ratID = ratRef.getKey();
        r.setUniqueKey(ratID.substring(ratID.lastIndexOf('-') + 1));
        ratRef.setValue(r);//sets available spot to Rat r
        //^^this is what adds value to firebase, don't change unless you don't want a Rat
    }

    /**
     * Removes the rat specified from the database
     * @param r The rat to be removed from the database
     */
    public static void removeRat(Rat r) {dbRef.child("rats/"+r.getUniqueKey()).removeValue();}

    /**
     * Updates the rats in the given map. Entries with null Objects are removed
     * @param m the Map containing all Rats to be updated.
     */
    public static void updateRats(Map<String, Object> m) {dbRef.child("rats").updateChildren(m);}

    /**
     * Adds a listener to the rats database that calls makeList() every time data is updated
     */
    public static void addListener(){
        DatabaseReference dbTemp = FirebaseDatabase.getInstance().getReference().child("rats");
        //only way i could find to get data from fireBase
        dbTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    RatFB.makeList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * Puts all the Rats in the DataSnapshot into a map, then updates the map in RatFB
     * @param data The snapshot of the data in rats. Contains all of the rats in the database
     */
    private static void makeList(DataSnapshot data){
        Log.d("TEST", "MakeList called");
        Map<String, Object> m = new HashMap<String, Object>();
        Rat[] rats = new Rat[(int) data.getChildrenCount()];
        int i = 0;
        for (DataSnapshot snap : data.getChildren()) {
            Object value = snap.getValue(Rat.class);
            String key = (String) ((Rat) value).getUniqueKey();
            if (key == null) {
                key = "invalidInput";
                Log.d("TEST", "Found a pesky rat. ID: " + ((Rat) value).getUniqueKey() + "\nnumber: " + i);
            }
            m.put(key, value);
            //Log.d("TEST", "successful put #"+i);
            rats[i] = (Rat) value;
            i++;
        }
        RatFB.setAllRats(rats);
        RatFB.setMasterMap(m);

        if (firstCall) {
                Log.d("TEST", "got that info BOIIIII");
        }
        Log.d("TEST", "madeList of size: "+allRats.length);

    }

    /**
     * Retrieves a rat from the database with the given uniqueID
     * @param key The nuique ID of the rat to be retrieved
     * @return The rat who has the given unique ID
     */
    public static Rat getRat(String key) {return (Rat)masterMap.get(key);}

    /**
     * Gets the map of all rats in the Database
     * @return The map of all the rats in the database
     */
    public static Map<String, Object> getMasterMap()   {return masterMap;}

    /**
     * Sets the map of all Rats in the dataBase. Does not affect the Database.
     * @param m the map to be set
     */
    public static void setMasterMap(Map<String, Object> m) {masterMap = m;}

    /**
     * Sets allRats to the given Rat[]
     * @param r Array of rats to set allRats to
     */
    public static void setAllRats(Rat[] r) {allRats = r;}

    /**
     * Returns an Array of all the rats in the dataBase
     * @return an Array of all the rats in the database
     */
    public static Rat[] getAllRats()   {return allRats;}


}


