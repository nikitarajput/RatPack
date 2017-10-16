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
    private FirebaseDatabase fbDB;
    private DatabaseReference dbRef;
    private Map<String, Object> masterMap;
    private Rat[] allRats;
    private volatile Boolean firstCall;
    private final Object lock= new Object();

    public RatFB(){
        Log.d("TEST", "Called constructor for RatFB");
        fbDB = FirebaseDatabase.getInstance();
        dbRef = fbDB.getReference();
        masterMap = new HashMap<String, Object>();
        allRats = new Rat[0];
        firstCall = true;
        Log.d("TEST","initialized all variables");
        addListener();
    }
    
    /**
     * Adds a rat to the database and gives it a unique ID
     * @param r rat to be added to database
     */
    public void addRat(Rat r){
        DatabaseReference ratRef = dbRef.child("rats").push();

        String ratID = ratRef.getKey();
        r.setUniqueKey(ratID.substring(ratID.lastIndexOf('-') + 1));
        ratRef.setValue(r);
    }

    /**
     * Removes the rat specified from the database
     * @param r The rat to be removed from the database
     */
    public void removeRat(Rat r) {dbRef.child("rats/"+r.getUniqueKey()).removeValue();}

    /**
     * Updates the rats in the given map. Entries with null Objects are removed
     * @param m the Map containing all Rats to be updated.
     */
    public void updateRats(Map<String, Object> m) {dbRef.child("rats").updateChildren(m);}

    /**
     * Adds a listener to the rats database that calls makeList() every time data is updated
     */
    public void addListener(){
        DatabaseReference dbTemp = FirebaseDatabase.getInstance().getReference().child("rats");
        //only way i could find to get data from fireBase
        dbTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    makeList(dataSnapshot);
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
    private void makeList(DataSnapshot data){
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
        this.setAllRats(rats);
        this.setMasterMap(m);

        if (firstCall) {//updates the rat sightings activity when data is first recieved
            //Rat_Sightings_Activity.forFirstData.callOnClick();
            //Rat_Sightings_Activity.forFirstData = null;
                Log.d("TEST", "got that info BOIIIII");
        }
        Log.d("TEST", "madeList of size: "+allRats.length);

    }

    /**
     * Retrieves a rat from the database with the given uniqueID
     * @param key The nuique ID of the rat to be retrieved
     * @return The rat who has the given unique ID
     */
    public Rat getRat(String key) {return (Rat)masterMap.get(key);}

    /**
     * Gets the map of all rats in the Database
     * @return The map of all the rats in the database
     */
    public Map<String, Object> getMasterMap()   {return this.masterMap;}

    /**
     * Sets the map of all Rats in the dataBase. Does not affect the Database.
     * @param m the map to be set
     */
    public void setMasterMap(Map<String, Object> m) {this.masterMap = m;}

    /**
     * Sets allRats to the given Rat[]
     * @param r Array of rats to set allRats to
     */
    public void setAllRats(Rat[] r) {this.allRats = r;}

    /**
     * Returns an Array of all the rats in the dataBase
     * @return an Array of all the rats in the database
     */
    public Rat[] getAllRats()   {return this.allRats;}


}
    //get rat


