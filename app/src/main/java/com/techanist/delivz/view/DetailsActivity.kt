package com.techanist.delivz.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.techanist.delivz.R
import com.techanist.delivz.data.dto.Delivery
import com.techanist.delivz.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    val delivery: Delivery? by lazy {
        intent.getSerializableExtra("item") as Delivery?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(this, R.layout.activity_details) as ActivityDetailsBinding
        binding.setDelivery(delivery)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        if (delivery == null) return
        val latLng = LatLng(delivery!!.location.lat, delivery!!.location.lng)
        mMap.addMarker(MarkerOptions().position(latLng).title(delivery!!.location.address))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
    }
}
