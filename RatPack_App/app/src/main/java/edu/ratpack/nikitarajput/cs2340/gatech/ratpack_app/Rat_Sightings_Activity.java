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
    //this will be replaced with our list of rats from firebase
    //all methods involving the list of rats will be fully implemented in the Rat class
    //currently they are just place holders for testing/debugging
    static String[] oldRatListStandIn = new String[]{"Sleepy", "Ratty", "Fluffy", "Dopey"};
    static String[] ratListStandIn = new String[]{"Newbie", "Sleepy", "Ratty", "Fluffy", "Dopey", "asds", "asdasd", "asdsads", "asdasds", "asdsads", "asdsads"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sightings);

        Rat_Sightings_Activity.setRatList();
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_rat_sightings);
        for(int i = 0; i<ratListStandIn.length; i++){//adds the buttons to the Layout
            Button butt = new Button(this);

            butt.setText("Rat: "+ratListStandIn[i]);//changed later
            butt.setId(i);
            butt.setBackgroundColor(Color.WHITE);
            butt.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            butt.setHeight(findViewById(R.id.add_rat_button).getHeight());
            butt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Rat_Sightings_Activity.this.viewRat(v);
                }
            });
            layout.addView(butt);
        }
    }

    public void reload(View v) {//I thought this would be nice to have. We can get rid of it if we need
        // updates ratListStandIn
        Rat_Sightings_Activity.setRatList();
        LinearLayout layout = (LinearLayout) v.getParent();
        int larger = (ratListStandIn.length>oldRatListStandIn.length?
                ratListStandIn.length: oldRatListStandIn.length);
        int smaller =(ratListStandIn.length<oldRatListStandIn.length?
                ratListStandIn.length: oldRatListStandIn.length);
        for (int i = 0; i < larger; i++) {//adds new buttons
            if(i < smaller){ //renaming buttons still on screen
                Button butt = (Button)findViewById(i);
                butt.setText("Rat: " + ratListStandIn[i]);//changed later
            } else if(ratListStandIn.length > oldRatListStandIn.length){//adds extra if adding
                Button butt = new Button(this);

                butt.setText("Rat: " + ratListStandIn[i]);//changed later
                butt.setId(i);
                butt.setBackgroundColor(Color.WHITE);
                butt.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                butt.setHeight(findViewById(R.id.add_rat_button).getHeight());
                butt.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Rat_Sightings_Activity.this.viewRat(v);
                    }
                });
                layout.addView(butt);
            } else{//removes extra if removing
                layout.removeView(findViewById(i));
            }
        }
    }

    public void onBack(View v){
        startActivity(new Intent(Rat_Sightings_Activity.this, Home_Activity.class));
    }

    public void addRat(View v){
        startActivity(new Intent(Rat_Sightings_Activity.this, Rat_Input_Activity.class));

    }

    public void viewRat(View v){
        startActivity(new Intent(Rat_Sightings_Activity.this, Rat_Details_Activity.class));
    }

    public static void setRatList(){//will be a moved to rat class later
        String[] temp = ratListStandIn;
        ratListStandIn = oldRatListStandIn;
        oldRatListStandIn = temp;
    }


}
