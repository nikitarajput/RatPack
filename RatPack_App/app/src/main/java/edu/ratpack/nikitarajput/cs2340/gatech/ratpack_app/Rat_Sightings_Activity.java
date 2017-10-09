package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
//import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Rat_Sightings_Activity extends AppCompatActivity {
    private static Rat[] ratList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sightings);
        updateRatList();
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_rat_sightings);
        for (int i = 0; i < ratList.length; i++) {//adds the buttons to the Layout
            Button butt = new Button(this);
            //rat button, id is even number
            butt.setText("Rat: " + ratList[i].getName());//changed later
            butt.setId(2 * i);
            butt.setBackgroundColor(Color.WHITE);
            butt.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            butt.setHeight(findViewById(R.id.add_rat_button).getHeight());
            butt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    viewRat(findViewById(v.getId() + 1));
                }
            });
            //rat lstView for info, id is odd
            TextView buttDetails = new TextView(this);
            buttDetails.setText("Name: " + ratList[i].getName() + "\nAddress: " + ratList[i].getAddress()
                    + "\nCity: " + ratList[i].getCity() + "\nZipcode: " + ratList[i].getZipCode()
                    + "\nLocation Type: " + ratList[i].getLocationType() + "\nBorough: " + ratList[i].getBorough().toString()
                    + "\nDate: " + ratList[i].getDate() + "\nTime: " + ratList[i].getTime()
                    + "\nLatitude: " + ratList[i].getLatitude() + "\nLongitude: " + ratList[i].getLongitude());
            buttDetails.setId(2 * i + 1);
            buttDetails.setVisibility(View.GONE);

            layout.addView(butt);
            layout.addView(buttDetails);
        }
    }

    public void reload(View v) {//I thought this would be nice to have. We can get rid of it if we need
        // updates ratList
        Rat[] oldRatList = ratList;
        updateRatList();
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_rat_sightings);
        for (int i = 0; i < oldRatList.length; i++) {//adds new buttons
            layout.removeView(findViewById(2 * i));
            layout.removeView(findViewById(2 * i + 1));
        }
        for (int i = 0; i < ratList.length; i++) {//copy paste from onCreate
            Button butt = new Button(this);
            //rat button, id is even number
            butt.setText("Rat: " + ratList[i].getName());//changed later
            butt.setId(2 * i);
            butt.setBackgroundColor(Color.WHITE);
            butt.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            butt.setHeight(findViewById(R.id.add_rat_button).getHeight());
            butt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    viewRat(findViewById(v.getId() + 1));
                }
            });
            //rat lstView for info, id is odd
            TextView buttDetails = new TextView(this);
            buttDetails.setText("Name: " + ratList[i].getName() + "\nAddress: " + ratList[i].getAddress()
                    + "\nCity: " + ratList[i].getCity() + "\nZipcode: " + ratList[i].getZipCode()
                    + "\nLocation Type: " + ratList[i].getLocationType() + "\nBorough: " + ratList[i].getBorough().toString()
                    + "\nDate: " + ratList[i].getDate() + "\nTime: " + ratList[i].getTime()
                    + "\nLatitude: " + ratList[i].getLatitude() + "\nLongitude: " + ratList[i].getLongitude());
            buttDetails.setId(2 * i + 1);
            buttDetails.setVisibility(View.GONE);

            layout.addView(butt);
            layout.addView(buttDetails);
        }
    }

    public void onBack(View v) {
        startActivity(new Intent(Rat_Sightings_Activity.this, Home_Activity.class));
    }

    public void addRat(View v) {
        startActivity(new Intent(Rat_Sightings_Activity.this, Rat_Input_Activity.class));

    }

    public void viewRat(View v) {
        if (v.getVisibility() == View.GONE)
            findViewById(v.getId()).setVisibility(View.VISIBLE);
        else
            findViewById(v.getId()).setVisibility(View.GONE);

    }

    public static void updateRatList() {//will be a moved to rat class later
        Log.d("TEST rsa", "going to update list");
        ratList = Rat.updateList();
        Log.d("TEST rsa", "updated list to length: "+ratList.length);
        if (ratList.length != 0 && ratList.length != 4) {
            int count = ratList.length == 3 ? 5 : 3;
            ratList = new Rat[count];
            for (int i = 0; i < ratList.length; i++) {
                ratList[i] = new Rat();
            }
        }


    }
}
