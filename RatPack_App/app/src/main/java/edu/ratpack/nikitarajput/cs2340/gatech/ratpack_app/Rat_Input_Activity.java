package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Rat_Input_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat__input);

        Spinner locationTypeSpinner = (Spinner) findViewById(R.id.spinner_location_type);
        ArrayAdapter<CharSequence> locationTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.location_types, android.R.layout.simple_spinner_item);
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        Spinner boroughSpinner = (Spinner) findViewById(R.id.spinner_borough);
        ArrayAdapter<CharSequence> boroughAdapater = ArrayAdapter.createFromResource(this,
                R.array.boroughs, android.R.layout.simple_spinner_item);
        boroughAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapater);
    }
}
