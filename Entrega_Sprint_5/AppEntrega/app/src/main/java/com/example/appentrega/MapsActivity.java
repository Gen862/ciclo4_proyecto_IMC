package com.example.appentrega;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.appentrega.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private DatabaseReference mDatabase;
    private ArrayList<Marker> tmpRealTimeMarker =new ArrayList<>(); // para almacenar o borrar los marker de tiempo real y no los amontone
    private ArrayList<Marker> realTimeMarker =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        mDatabase.child("usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(Marker marker: realTimeMarker){
                    marker.remove();
                }
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                  MapsPojo mp = snapshot.getValue(MapsPojo.class);
                  Double latitud = mp.getLatitud();
                  Double longitud = mp.getLongitud();
                  MarkerOptions markerOptions = new MarkerOptions();
                  markerOptions.position(new LatLng(latitud,longitud));
                  tmpRealTimeMarker.add(mMap.addMarker(markerOptions));
              }
              realTimeMarker.clear();
              realTimeMarker.addAll(tmpRealTimeMarker);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError dataSnapshot) {

            }
        });

    }
}