package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.MapsActivity;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.DatePickerFragment;


public class Map_Range_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_range);
        mAuth = FirebaseAuth.getInstance();
    }

    public void toHome(View v){
        startActivity(new Intent(Map_Range_Activity.this, Home_Activity.class));
    }
    public void toRatMap(View v){
        startActivity(new Intent(Map_Range_Activity.this, MapsActivity.class));
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}