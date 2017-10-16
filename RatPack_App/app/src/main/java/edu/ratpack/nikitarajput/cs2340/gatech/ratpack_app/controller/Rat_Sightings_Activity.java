package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;


public class Rat_Sightings_Activity extends AppCompatActivity {
    private static Rat[] ratList =new Rat[0];

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sightings);
        Log.d("TEST", "This is a test. please display");
        reload(findViewById(R.id.reload_button));
    }

    /**
     * Refreshes rat sightings page.
     * @param v the current view that the data is coming from.
     */
    public void reload(View v) {//I thought this would be nice to have. We can get rid of it if we need

        Log.d("TEST", "Called Reload");
        Rat[] oldRatList = ratList;
        updateRatList();
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_rat_sightings);
        for (int i = 0; i < oldRatList.length; i++) {//removes old buttons

            layout.removeView(findViewById(2 * i));
            layout.removeView(findViewById(2 * i + 1));
        }
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

    }

    /**
     * Takes user to home page.
     * @param v the current view that the data is coming from.
     */
    public void onBack(View v) {
        startActivity(new Intent(Rat_Sightings_Activity.this, Home_Activity.class));
    }

    /**
     * Takes user to add rat page.
     * @param v the current view that the data is coming from.
     */
    public void addRat(View v) {
        startActivity(new Intent(Rat_Sightings_Activity.this, Rat_Input_Activity.class));

    }

    /**
     * Shows rat details.
     * @param v the current view that the data is coming from.
     */
    public void viewRat(View v) {
        if (v.getVisibility() == View.GONE)
            findViewById(v.getId()).setVisibility(View.VISIBLE);
        else
            findViewById(v.getId()).setVisibility(View.GONE);

    }

    /**
     * Shows first 50 entries of rat sightings.
     */
    public static void updateRatList() {

        Log.d("TEST", "entered updateRatList()");
        Rat[] temp = RatFB.getAllRats();
        if (temp.length > 50){
            ratList = new Rat[50];
            for(int i = 0; i < ratList.length; i++){
                ratList[i] = temp[i];
            }
        }
        else
            ratList = temp;
        Log.d("TEST", "finished method. Updated ist to length: "+ratList.length);
    }
}
