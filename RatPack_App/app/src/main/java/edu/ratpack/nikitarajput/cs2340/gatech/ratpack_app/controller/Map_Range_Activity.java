package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.MapsActivity;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.DatePickerFragment;

/**
 * Class that displays a calendar for users to pick a date range for rat sightings.
 */
public class Map_Range_Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_range);
    }

    /**
     * Takes user to home screen.
     * @param v current view
     */
    public void toHome(View v){
        startActivity(new Intent(Map_Range_Activity.this, Home_Activity.class));
    }

    /**
     * Takes user to map.
     * @param v current view
     */
    public void toRatMap(View v){
        startActivity(new Intent(Map_Range_Activity.this, MapsActivity.class));
    }

    /**
     * Shows date picker for users to select a date range to see rat sightings from.
     * @param v current view
     */
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}