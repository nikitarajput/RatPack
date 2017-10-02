package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


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
}