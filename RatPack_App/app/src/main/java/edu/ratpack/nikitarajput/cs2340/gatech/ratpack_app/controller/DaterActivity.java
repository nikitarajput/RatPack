package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;

public class DaterActivity extends AppCompatActivity {

    private Spinner startYear, startMonth, endYear, endMonth;
    private int startYearInt, startMonthInt, endYearInt, endMonthInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dater);

        startYear = (Spinner) findViewById(R.id.startYear);
        startMonth = (Spinner) findViewById(R.id.startMonth);
        endYear = (Spinner) findViewById(R.id.endYear);
        endMonth = (Spinner) findViewById(R.id.endMonth);

        ArrayList<Integer> years = new ArrayList<Integer>();
        for(int i = 1990; i <= 2017; i++){
            years.add(i);
        }

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startYear.setAdapter(yearAdapter);
        endYear.setAdapter(yearAdapter);

        String[] monthsArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        ArrayList<String> months = new ArrayList<String>(Arrays.asList(monthsArray));

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startMonth.setAdapter(monthAdapter);
        endMonth.setAdapter(monthAdapter);

        startYear.setSelection(27);
        endYear.setSelection(27);
        endMonth.setSelection(9);


    }


    private int parseMonth(String month){
        if(month.contains("Jan"))
            return 1;
        else if(month.contains("Feb"))
            return 2;
        else if(month.contains("Mar"))
            return 3;
        else if(month.contains("Apr"))
            return 4;
        else if(month.contains("May"))
            return 5;
        else if(month.contains("Jun"))
            return 6;
        else if(month.contains("Jul"))
            return 7;
        else if(month.contains("Aug"))
            return 8;
        else if(month.contains("Sep"))
            return 9;
        else if(month.contains("Oct"))
            return 10;
        else if(month.contains("Nov"))
            return 11;
        else
            return 12;

    }



    public void toGraph(View v){
        startYearInt = Integer.parseInt(startYear.getSelectedItem().toString());
        startMonthInt = parseMonth(startMonth.getSelectedItem().toString());
        endYearInt = Integer.parseInt(endYear.getSelectedItem().toString());
        endMonthInt = parseMonth(endMonth.getSelectedItem().toString());

        Intent intent = new Intent(DaterActivity.this, GraphActivity.class);
        intent.putExtra("startYear", startYearInt);
        intent.putExtra("startMonth", startMonthInt);
        intent.putExtra("endYear", endYearInt);
        intent.putExtra("endMonth", endMonthInt);

        startActivity(intent);

    }
}
