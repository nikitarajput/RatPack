package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.MapsActivity;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;


public class Home_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onLogout(View v) {
        mAuth.signOut();
        startActivity(new Intent(Home_Activity.this,Welcome_Activity.class));
    }
    public void toRatSightings(View v){
        startActivity(new Intent(Home_Activity.this, Rat_Sightings_Activity.class));
    }
    public void toMapRange(View v){
        startActivity(new Intent(Home_Activity.this, Map_Range_Activity.class));
    }

    public void toDataRange(View v){
        startActivity(new Intent(Home_Activity.this, DaterActivity.class));
    }
}