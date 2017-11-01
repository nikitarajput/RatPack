package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Geocoder;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static Rat[] ratList = new Rat[0];
    public Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng nyc = new LatLng(40.7829, -73.9654);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(nyc));
        mMap.setMinZoomPreference(11);

        Rat[] currentRatList= RatFB.getAllRats();
        for (int i = 0; i < currentRatList.length; i++) {
            if (isValidDate(currentRatList[i])) {
                double currLat = currentRatList[i].getLatitude();
                double currLong = currentRatList[i].getLongitude();
                LatLng currLatLong = new LatLng(currLat, currLong);
                mMap.addMarker(new MarkerOptions().position(currLatLong)
                        .title("Rat ID: " + currentRatList[i].getUniqueKey())
                        .snippet("Rat Name: " + currentRatList[i].getName() + "\n"
                                + "Date Created: " + currentRatList[i].getDate()
                                + "\n" + "Address: " + currentRatList[i].getAddress()
                                + "\n" + "Borough: " + currentRatList[i].getBorough()
                                + "\n" + "Location Type: " + currentRatList[i].getLocationType()));
            }
        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                Context context = MapsActivity.this;

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }

    /**
     * Checks if the rat's creation date is in the correct range.
     * @param rat the rat to check the creation date of.
     * @return true if the creation date is in the range and false otherwise.
     */
    private boolean isValidDate(Rat rat) {
        Intent myIntent = getIntent();
        String year = myIntent.getStringExtra("year");
        String month = myIntent.getStringExtra("month");
        String day = myIntent.getStringExtra("day");
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        DateFormat originalFormat = new SimpleDateFormat("MMM dd, yyyy");
        DateFormat targetFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = originalFormat.parse(rat.getDate());
            String formattedDate = targetFormat.format(date);
            String createDateYear = formattedDate.substring(0, 4);
            String createDateMonth = formattedDate.substring(4, 6);
            String createDateDay = formattedDate.substring(6, 8);
            int createDateYearInt = Integer.parseInt(createDateYear);
            int createDateMonthInt = Integer.parseInt(createDateMonth);
            int createDateDayInt = Integer.parseInt(createDateDay);
            if (createDateYearInt >= yearInt && createDateMonthInt >= monthInt && createDateDayInt >= dayInt) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.d("Exception", "Could not parse the date.");
        }
        return true;
    }

}
