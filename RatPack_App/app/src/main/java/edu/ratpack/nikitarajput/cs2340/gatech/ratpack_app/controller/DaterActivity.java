package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;

public class DaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dater);
    }






    public void toGraph(View v){
        startActivity(new Intent(DaterActivity.this, GraphActivity.class));

    }
}
