package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rat_Input_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;
    private Spinner locationTypeSpinner;
    private Spinner boroughSpinner;
    private EditText ratName;
    private EditText incidentAddress;
    private EditText incidentCity;
    private EditText incidentZipCode;
    private static final String TAG = "AddToDatabase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat__input);

        locationTypeSpinner = (Spinner) findViewById(R.id.spinner_location_type);
        boroughSpinner = (Spinner) findViewById(R.id.spinner_borough);
        ratName = (EditText) findViewById(R.id.rat_name_editText);
        incidentAddress = (EditText) findViewById(R.id.incident_address_editText);
        incidentCity = (EditText) findViewById(R.id.incident_city_editText);
        incidentZipCode = (EditText) findViewById(R.id.incident_zipcode_editText);

        ArrayAdapter<String> locationTypeAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Rat.LocationType.values());
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        ArrayAdapter<String> boroughAdapater = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Rat.Borough.values());
        boroughAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapater);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();
    }

    public void addRat(View v) {
        Log.d(TAG, ratName.getText().toString());
        Rat rat = new Rat(ratName.getText().toString(), (Rat.LocationType) locationTypeSpinner.getSelectedItem(),
                incidentAddress.getText().toString(), incidentCity.getText().toString(),
                Integer.parseInt(incidentZipCode.getText().toString()),
                (Rat.Borough) boroughSpinner.getSelectedItem());
        DatabaseReference ratRef = dbRef.child("rats");
        String key = ratRef.push(rat);





    }
}
