package com.viajeros.pe.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viajeros.pe.R;

import com.viajeros.pe.firebase.model.TouristPlace;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

    Button buttonTodo;
    FloatingActionButton fbaBack, fbaNext;
    private ArrayList<LatLng> locationArrayList;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLng EPIS = new LatLng(-16.406481373889534, -71.52456067325008);

            HomeViewModel homeViewModel = new HomeViewModel();

            locationArrayList = new ArrayList<>();
            homeViewModel.getAllLiveData().observe(getViewLifecycleOwner(), places ->{
                places.forEach(touristPlace -> {
                    String xValue = touristPlace.getCoordinates().substring(0,touristPlace.getCoordinates().indexOf(','));
                    String yValue = touristPlace.getCoordinates().substring(touristPlace.getCoordinates().indexOf(',')+1, touristPlace.getCoordinates().length());
                    if ( !xValue.trim().equals("") && !yValue.trim().equals("") )
                    {
                        double Hlat = Double.parseDouble(xValue.trim());
                        double Hlong= Double.parseDouble(yValue.trim());
                        locationArrayList.add(new LatLng(Hlat, Hlong));
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(Hlat, Hlong)).title(touristPlace.getDescription()));
                    }

                });
            });
            for (int i = 0; i < locationArrayList.size(); i++) {
                googleMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title(locationArrayList.get(0).latitude+""));
            }

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EPIS,13));


        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

}