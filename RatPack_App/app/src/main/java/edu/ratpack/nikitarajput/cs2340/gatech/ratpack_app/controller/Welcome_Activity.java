package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;

/**
 * Class that displays a Welcome Screen.
 */
public class Welcome_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        autoLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

    /**
     * Takes user to Login Screen.
     * @param view current view
     */
    public void toLoginActivity(View view){
        startActivity(new Intent(Welcome_Activity.this, Login_Activity.class));
    }

    /**
     * Takes user to Registration Screen
     * @param view current view
     */
    public void toRegisterActivity(View view){
        startActivity(new Intent(Welcome_Activity.this, Register_Activity.class));
    }

    /**
     * Automatically attempts login on start if the user is logged in on Firebase
     */
    private void autoLogin(){
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbR = db.getReference();
        DatabaseReference dbRef = dbR.child("users");
        try {
            //noinspection ConstantConditions
            @SuppressWarnings("ConstantConditions")
            String current = mAuth.getCurrentUser().getUid();
            if (dbRef.child(current) != null) {
                RatFB.init();
                Toast t =Toast.makeText(getApplicationContext(),
                        "Logging in...", Toast.LENGTH_SHORT);
                t.show();
                startActivity(new Intent(Welcome_Activity.this, Home_Activity.class));
            }
        } catch (Exception e){
            Log.d("TEST", "autoLogin: Null current user");
        }
    }
}
