package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.R;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Geocoder;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Reader;

/**
 * Class that records rat sighting.
 */
public class Rat_Input_Activity extends AppCompatActivity {


    private EditText ratName;
    private EditText address;
    private EditText zipCode;
    private EditText city;


    private Spinner locationTypeSpinner;
    private Spinner boroughSpinner;
    private static final String TAG = "AddToDatabase";
    private Geocoder geocoder;
    private Rat rat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_input);

        locationTypeSpinner = (Spinner) findViewById(R.id.spinner_location_type);
        boroughSpinner = (Spinner) findViewById(R.id.spinner_borough);
        ratName = (EditText)findViewById(R.id.rat_name_editText);
        address = (EditText)findViewById(R.id.incident_address_editText);
        zipCode = (EditText)findViewById(R.id.incident_zip_editText);
        city = (EditText)findViewById(R.id.incident_city_editText);

        ArrayAdapter<CharSequence> locationTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.location_types, android.R.layout.simple_spinner_item);
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(this,
                R.array.boroughs, android.R.layout.simple_spinner_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(Adapter);

        geocoder = new Geocoder();

    }

    /**
     * Reads CSV file of rat sightings.
     * @param v the current view that the data is coming from.
     */
    public void readCSV(View v){
        Map<String, Object> temp = Reader.updateMap(this);
        Log.d("TEST", "\nFinished updating Map, is size:"+temp.size());
        RatFB.updateRats(temp);
    }

    /**
     * Adds rat to Firebase.
     * @param v is the current view
     */
    public void addRat(View v) {
        Toast response = Toast.makeText(Rat_Input_Activity.this,
                "", Toast.LENGTH_SHORT);
        if(isValid()) {
            Editable name = ratName.getText();
            Object locType = locationTypeSpinner.getSelectedItem();
            Editable addr = address.getText();
            Editable cit = city.getText();
            Editable zip = zipCode.getText();
            Object borough = boroughSpinner.getSelectedItem();
            String z = zip.toString();
            String n = name.toString();
            String l = locType.toString();
            String c = cit.toString();
            String a = addr.toString();
            int zi = Integer.parseInt(z);
            String b = borough.toString();

            Log.d(TAG, n);
            rat = new Rat(n, l, a, c, zi, b);
            geocode(geocoder.buildURL(rat.getAddress(), rat.getCity()));
            response.setText("Rat added!");

        }
        else{
            response.setText("Please fill all fields.");
        }
        response.show();

    }

    /**
     * makes Google Map's API call to geocode rat location
     * @param url custom url used to make API call
     */
    private void geocode(String url) {
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        geocoder.parseData(response);
                        rat.setLatitude(geocoder.getLat());
                        rat.setLongitude(geocoder.getLong());
                        Log.d("LATITUDE of rat", "" + rat.getLatitude());
                        Log.d("LONGITUDE of rat", "" + rat.getLongitude());
                        RatFB.addRat(rat);
                        toSightingsActivity();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
        };

        mQueue.add(jsonObjectRequest);
    }


    /**
     * Checks validity by checking for empty fields, and for length of the inputs.
     * @return a message saying what went wrong. Returns "" if there was a success.
     */
    private boolean isValid(){
        Editable name = ratName.getText();
        Object locType = locationTypeSpinner.getSelectedItem();
        Editable addr = address.getText();
        Editable cit = city.getText();
        Editable zip = zipCode.getText();
        Object borough = boroughSpinner.getSelectedItem();
        String z = zip.toString();
        String n = name.toString();
        String a = addr.toString();
        String c = cit.toString();

        return !(n.matches("") || a.matches("") ||
                z.matches("") || c.matches(""));
    }

    /**
     * Takes user to rat sightings page.
     */
    private void toSightingsActivity(){
        startActivity(new Intent(Rat_Input_Activity.this, Rat_Sightings_Activity.class));
    }
}







