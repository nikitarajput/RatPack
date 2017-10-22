package edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.Rat;
import edu.ratpack.nikitarajput.cs2340.gatech.ratpack_app.model.RatFB;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static Rat[] ratList =new Rat[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng nyc = new LatLng(40.7128, -73.935242);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(nyc));
        mMap.setMinZoomPreference(12);

        Rat[] currentRatList= RatFB.getAllRats();
        for (int i = 0; i < currentRatList.length; i++) {
            double currLat = currentRatList[i].getLatitude();
            double currLong = currentRatList[i].getLongitude();
            LatLng currLatLong = new LatLng(currLat, currLong);
            mMap.addMarker(new MarkerOptions().position(currLatLong).title("Marker" + i));
        }
    }

}
