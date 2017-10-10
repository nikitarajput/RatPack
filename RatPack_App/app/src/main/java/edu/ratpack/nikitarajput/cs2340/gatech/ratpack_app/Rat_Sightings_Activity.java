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
    private static Rat[] ratList =new Rat[0];
    public static Button forRat;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sightings);
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_rat_sightings);
        forRat = (Button)findViewById(R.id.reload_button);
        reload(findViewById(R.id.reload_button));
    }

    public void reload(View v) {//I thought this would be nice to have. We can get rid of it if we need

        Rat[] oldRatList = ratList;
        Log.d("TEST","Begin reload, calls updateRatList()");
        updateRatList();
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_rat_sightings);
        Log.d("TEST","Removing "+oldRatList.length+" button-textview pairs");
        for (int i = 0; i < oldRatList.length; i++) {//removes old buttons

            layout.removeView(findViewById(2 * i));
            layout.removeView(findViewById(2 * i + 1));
        }
        Log.d("TEST","adding "+ratList.length+" button-textView pairs");
        for (int i = 0; i < ratList.length; i++) {//adds new buttons
            //rat button
            Button butt = new Button(this);
            butt.setText("Rat: " + ratList[i].getName());
            butt.setId(2 * i);
            butt.setBackgroundColor(Color.WHITE);
            butt.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            butt.setHeight(findViewById(R.id.add_rat_button).getHeight());
            butt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    viewRat(findViewById(v.getId() + 1));
                }
            });
            //rat lstView for info
            TextView buttDetails = new TextView(this);
            buttDetails.setText("Unique ID: " + ratList[i].getUniqueKey()
                    + "\nName: " + ratList[i].getName() + "\nAddress: " + ratList[i].getAddress()
                    + "\nCity: " + ratList[i].getCity() + "\nZipcode: " + ratList[i].getZipCode()
                    + "\nLocation Type: " + ratList[i].getLocationType() + "\nBorough: " + ratList[i].getBorough()
                    + "\nDate: " + ratList[i].getDate() + "\nTime: " + ratList[i].getTime()
                    + "\nLatitude: " + ratList[i].getLatitude() + "\nLongitude: " + ratList[i].getLongitude());
            buttDetails.setId(2 * i + 1);
            buttDetails.setTextSize(20f);
            buttDetails.setVisibility(View.GONE);

            layout.addView(butt);
            layout.addView(buttDetails);
        }
        Log.d("TEST","End reload()\n**********************");

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
        Log.d("TEST rsa", "updated ratList to length: "+ratList.length+"\n*********************");

    }
}
