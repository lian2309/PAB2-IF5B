package com.if5b.myFirstGoogleMap;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Lokasi {
    private String nama;
    private LatLng latLng;

    public Lokasi() {
    }

    public Lokasi(String nama, LatLng latLng) {
        this.nama = nama;
        this.latLng = latLng;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
