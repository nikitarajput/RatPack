package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
        mMap.setMinZoomPreference(12);

        Rat[] currentRatList= RatFB.getAllRats();
        for (int i = 0; i < currentRatList.length; i++) {
            double currLat = currentRatList[i].getLatitude();
            double currLong = currentRatList[i].getLongitude();
            LatLng currLatLong = new LatLng(currLat, currLong);
            mMap.addMarker(new MarkerOptions().position(currLatLong)
                    .title("Rat ID: " + currentRatList[i].getUniqueKey())
                    .snippet("Rat Name: " + currentRatList[i].getName() + "\n"
                            + "Date Created: " + currentRatList[i].getDate()
                            + "\n" + "Borough: " + currentRatList[i].getBorough()
                            + "\n" + "Location Type: " + currentRatList[i].getLocationType()));
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

}
