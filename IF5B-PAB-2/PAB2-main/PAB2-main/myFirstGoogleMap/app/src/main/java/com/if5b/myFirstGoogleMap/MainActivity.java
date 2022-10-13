package com.if5b.myFirstGoogleMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.if5b.myFirstGoogleMap.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityMainBinding binding;
    private GoogleMap mMap;
    private List<Lokasi> restaurantList = new ArrayList<>();
    private List<Lokasi> hospitalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        restaurantList.add(new Lokasi("Omah Kopi", new LatLng(-2.9751239256403363, 104.73942018135384)));
        restaurantList.add(new Lokasi("Walka Coffee", new LatLng(-2.975504285891907, 104.73986006361164)));
        restaurantList.add(new Lokasi("Kopi Benawa", new LatLng(-2.9742882040632708, 104.73951137645606)));
        restaurantList.add(new Lokasi("Caramel Coffee & Resto", new LatLng(-2.977116117565618, 104.73757348564558)));
        restaurantList.add(new Lokasi("Nobu Bistro", new LatLng(-2.9738864146180655, 104.73833656896264)));
        restaurantList.add(new Lokasi("Bakso Cartel", new LatLng(-2.9745453492313674, 104.73797178855372)));
        restaurantList.add(new Lokasi("Kedai Wakaka", new LatLng(-2.9762007198151896, 104.73974204642052)));
        restaurantList.add(new Lokasi("Kota Kampus", new LatLng(-2.9758846460122474, 104.73920024022499)));
        restaurantList.add(new Lokasi("Kami Coffee and Resto", new LatLng(-2.973221885258028, 104.73804054777416)));
        restaurantList.add(new Lokasi("Richeese Factory Sumpah Pemuda Palembang", new LatLng(-2.972488186177403, 104.7388595996899)));

        hospitalList.add(new Lokasi("Rumah Sakit RK. Charitas", new LatLng(-2.974564328931667, 104.75232212019732)));
        hospitalList.add(new Lokasi("RS Siloam Sriwijaya Palembang", new LatLng(-2.976983819524196, 104.74245163935566)));
        hospitalList.add(new Lokasi("Rumah Sakit Umum Pusat Dr. Mohammad Hoesin", new LatLng(-2.965583684717034, 104.75021931629853)));
        hospitalList.add(new Lokasi("RS Bhayangkara Mohamad Hasan Palembang", new LatLng(-2.9569692191662535, 104.73708722157745)));
        hospitalList.add(new Lokasi("Rumah Sakit Bunda Palembang", new LatLng(-2.9660122633421167, 104.73425480899057)));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.fabRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();

                for (int i = 0; i < restaurantList.size(); i++) {
                    mMap.addMarker(new MarkerOptions()
                            .position(restaurantList.get(i).getLatLng())
                            .title(restaurantList.get(i).getNama()))
                            .showInfoWindow();
                }

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(restaurantList.get(4).getLatLng(),17));
            }
        });

        binding.fabHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();

                for (int i = 0; i < hospitalList.size(); i++) {
                    mMap.addMarker(new MarkerOptions()
                                    .position(hospitalList.get(i).getLatLng())
                                    .title(hospitalList.get(i).getNama())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                            .showInfoWindow();
                }

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospitalList.get(1).getLatLng(),15));
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMyLocationEnabled(true);
        LatLng latLngUser = new LatLng(-2.9734993100314244, 104.76468592785756);
        mMap.addMarker(new MarkerOptions().position(latLngUser).title("My Location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngUser, 13));
    }
}