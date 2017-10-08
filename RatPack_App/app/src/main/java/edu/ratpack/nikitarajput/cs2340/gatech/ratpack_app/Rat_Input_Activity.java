package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Integer.parseInt;

public class Rat_Input_Activity extends AppCompatActivity {


    EditText ratName, address, zipCode, city;
    Spinner locationTypeSpinner, boroughSpinner;
    double longitude, latitude;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat__input);

        ratName = (EditText)findViewById(R.id.rat_name_editText);
        address = (EditText)findViewById(R.id.incident_address_editText);
        zipCode = (EditText)findViewById(R.id.incident_zip_editText);
        city = (EditText)findViewById(R.id.incident_city_editText);

        locationTypeSpinner = (Spinner) findViewById(R.id.spinner_location_type);
        locationTypeSpinner.setAdapter(new ArrayAdapter<Rat.LocationType>(this, android.R.layout.simple_spinner_dropdown_item, Rat.LocationType.values()));

        boroughSpinner = (Spinner) findViewById(R.id.spinner_borough);
        boroughSpinner.setAdapter(new ArrayAdapter<Rat.Borough>(this, android.R.layout.simple_spinner_dropdown_item, Rat.Borough.values()));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();

    }


    public void onSubmit(View view){
        longitude = 0;
        latitude = 0;

        writeNewRat(longitude, latitude, locationTypeSpinner.getSelectedItem().toString(),
                parseInt(zipCode.getText().toString()), address.getText().toString(),
                city.getText().toString(), boroughSpinner.getSelectedItem().toString());

        startActivity(new Intent(Rat_Input_Activity.this, Rat_Sightings_Activity.class));
    }


    private void writeNewRat(double longitude, double latitude, String locationType, int zipCode, String address, String city, String borough) {
        String s = locationType;

        Rat.LocationType lll = Rat.LocationType.fromString(locationType);
        Rat remy = new Rat(longitude, latitude, Rat.LocationType.fromString(locationType), zipCode, address, city, Rat.Borough.fromString(borough));
        dbRef.child("rats").child(ratName.toString()).setValue(remy);
    }






}







