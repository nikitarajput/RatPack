package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Rat_Input_Activity extends AppCompatActivity {


    EditText ratName, address, zipCode, city;


    private Spinner locationTypeSpinner;
    private Spinner boroughSpinner;
    private static final String TAG = "AddToDatabase";
    public Geocoder geocoder;
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

        ArrayAdapter<CharSequence> boroughAdapater = ArrayAdapter.createFromResource(this,
                R.array.boroughs, android.R.layout.simple_spinner_item);
        boroughAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapater);

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
        if(isValid()) {
            Log.d(TAG, ratName.getText().toString());
            rat = new Rat(ratName.getText().toString(), locationTypeSpinner.getSelectedItem().toString(),
                    address.getText().toString(), city.getText().toString(),
                    Integer.parseInt(zipCode.getText().toString()),
                    boroughSpinner.getSelectedItem().toString());
            geocode(geocoder.buildURL(rat.getAddress(), rat.getCity()));
            Toast.makeText(Rat_Input_Activity.this, "Rat added!.",
                    Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(Rat_Input_Activity.this, "Please fill all fields.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * makes Google Map's API call to geocode rat location
     * @param url custom url used to make API call
     */
    public void geocode(String url) {
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        JSONObject locationDetails = response;
                        geocoder.parseData(locationDetails);
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
        String goodmsg = "";

        if(ratName.getText().toString().matches("") || address.getText().toString().matches("") ||
                zipCode.getText().toString().matches("") || city.getText().toString().matches("")) {
            return false;
        }
        return true;
    }

    /**
     * Takes user to rat sightings page.
     */
    public void toSightingsActivity(){
        startActivity(new Intent(Rat_Input_Activity.this, Rat_Sightings_Activity.class));
    }
}







