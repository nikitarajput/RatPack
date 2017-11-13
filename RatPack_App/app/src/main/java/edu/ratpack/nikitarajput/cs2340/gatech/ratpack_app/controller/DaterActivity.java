package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;

/**
 * Activity Viewer for activity_dater
 */
public class DaterActivity extends AppCompatActivity {

    private Spinner startYear;
    private Spinner startMonth;
    private Spinner endYear;
    private Spinner endMonth;
    public static final String[] monthsArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final int minYear  = 1990;
    private static final int maxYear  = Calendar.getInstance().get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dater);

        startYear = (Spinner) findViewById(R.id.startYear);
        startMonth = (Spinner) findViewById(R.id.startMonth);
        endYear = (Spinner) findViewById(R.id.endYear);
        endMonth = (Spinner) findViewById(R.id.endMonth);

        ArrayList<Integer> years = new ArrayList<>();
        for(int i = minYear; i <= maxYear; i++){
            years.add(i);
        }

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startYear.setAdapter(yearAdapter);
        endYear.setAdapter(yearAdapter);


        ArrayList<String> months = new ArrayList<>(Arrays.asList(monthsArray));

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startMonth.setAdapter(monthAdapter);
        endMonth.setAdapter(monthAdapter);


        final int thisYear = 27;
        startYear.setSelection(thisYear);
        endYear.setSelection(thisYear);

        final int thisMonth = 9;
        endMonth.setSelection(thisMonth);


    }

    /**
     * Returns what month the string corresponds to
     * @param month the string name for month
     * @return the int index of the month. starts at 1
     */
    private int parseMonth(String month){

        final int numMonthsInYear = 12;
        for(int i = 1; i <= numMonthsInYear; i++) {
            if(month.contains(monthsArray[i - 1])) {
                return i;
            }
        }
        return 0;
    }


    /**
     * Goes to Graph activity, but also puts in some preliminary data
     * @param v Goes to this activity
     */
    public void toGraph(@SuppressWarnings("unused") View v){

        Object startYearItem = startYear.getSelectedItem();
        Object startMonthItem = startMonth.getSelectedItem();
        Object endYearItem = endYear.getSelectedItem();
        Object endMonthItem = endMonth.getSelectedItem();
        int startYearInt = Integer.parseInt(startYearItem.toString());
        int startMonthInt = parseMonth(startMonthItem.toString());
        int endYearInt = Integer.parseInt(endYearItem.toString());
        int endMonthInt = parseMonth(endMonthItem.toString());

        if(((startYearInt * 100) + startMonthInt) >= ((endYearInt * 100) + endMonthInt)){
            Toast msg = Toast.makeText(DaterActivity.this, "Can't go backwards!",
                    Toast.LENGTH_SHORT);
            msg.show();
        }
        else {
            Intent intent = new Intent(DaterActivity.this, GraphActivity.class);
            intent.putExtra("startYear", startYearInt);
            intent.putExtra("startMonth", startMonthInt);
            intent.putExtra("endYear", endYearInt);
            intent.putExtra("endMonth", endMonthInt);

            startActivity(intent);
        }
    }
}
