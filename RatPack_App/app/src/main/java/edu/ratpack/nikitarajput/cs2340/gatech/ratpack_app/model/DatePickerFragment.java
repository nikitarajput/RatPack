package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.MapsActivity;

/**
 * Created by soniaggarwal on 10/23/17.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Activity activity = getActivity();
        Intent myIntent = new Intent(activity, MapsActivity.class);
        myIntent.putExtra("year",String.valueOf(year));
        myIntent.putExtra("month",String.valueOf(month));
        myIntent.putExtra("day",String.valueOf(day));
        startActivity(myIntent);
    }
}