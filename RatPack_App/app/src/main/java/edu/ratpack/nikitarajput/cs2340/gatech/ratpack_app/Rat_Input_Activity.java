package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.Map;

public class Rat_Input_Activity extends AppCompatActivity {


    EditText ratName, address, zipCode, city;
    String locationType, borough;

    private FirebaseApp fbApp = FirebaseApp.initializeApp(this);

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;
    private Spinner locationTypeSpinner;
    private Spinner boroughSpinner;
    private static final String TAG = "AddToDatabase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_input);

        locationTypeSpinner = (Spinner) findViewById(R.id.spinner_location_type);
        boroughSpinner = (Spinner) findViewById(R.id.spinner_borough);
        ratName = (EditText)findViewById(R.id.rat_name_editText);
        address = (EditText)findViewById(R.id.incident_address_editText);
        zipCode = (EditText)findViewById(R.id.incident_zip_editText);
        city = (EditText)findViewById(R.id.incident_city_editText);

        ArrayAdapter<CharSequence> locationTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.location_types, android.R.layout.simple_spinner_item);
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        ArrayAdapter<CharSequence> boroughAdapater = ArrayAdapter.createFromResource(this,
                R.array.boroughs, android.R.layout.simple_spinner_item);
        boroughAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapater);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();
    }

    /**
     * updates the children of the rats database to match those in the inputted Map
     * @param m the map of all values in the database to be updated.
     */
    public void reader(Map<String, Object> m){
        dbRef.child("rats").updateChildren(m);
    }//end of reader helper methods

    /**
     * Reads all the Rats in the CSV to Rat objects and adds them to FireBase
     * @param v the View that is calling this method
     */
    public void readCSV(View v){
        Map<String, Object> temp =Reader.updateMap(this);
        Log.d("TEST", "\nFinished updating Map, is size:"+temp.size());
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();
        reader(temp);
    }

    /**
     * Adds rat to Firebase
     * @param v is the current view
     */
    public void addRat(View v) {
        if(noEmptyFields()) {
            Log.d(TAG, ratName.getText().toString());
            Rat rat = new Rat(ratName.getText().toString(), locationTypeSpinner.getSelectedItem().toString(),
                    address.getText().toString(), city.getText().toString(),
                    Integer.parseInt(zipCode.getText().toString()),
                    boroughSpinner.getSelectedItem().toString());
            DatabaseReference ratsRef = dbRef.child("rats");

            DatabaseReference newRatRef = ratsRef.push();

            String ratID = newRatRef.getKey();
            rat.setUniqueKey(parseKey(ratID));
            newRatRef.setValue(rat);
            Toast.makeText(Rat_Input_Activity.this, "Rat added!.",
                    Toast.LENGTH_SHORT).show();
            toSightingsActivity();
        }
        else{
            Toast.makeText(Rat_Input_Activity.this, "Invalid rat.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private String parseKey(String url){
        return url.substring(url.lastIndexOf('-') + 1);
    }



    private boolean noEmptyFields(){
        if(ratName.getText().toString().matches("") || address.getText().toString().matches("") ||
                zipCode.getText().toString().matches("") || city.getText().toString().matches(""))
            return false;
        else
            return true;
    }


    public void toSightingsActivity(){
        startActivity(new Intent(Rat_Input_Activity.this, Rat_Sightings_Activity.class));
    }








}







