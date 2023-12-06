package com.test.application.person_details

import android.location.Address
import android.location.Geocoder
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.application.person_details.databinding.FragmentBirthPlaceMapBinding
import java.util.*

class MapsFragment (
    private var location : String?
) : Fragment() {

    private var _binding: FragmentBirthPlaceMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap
    private lateinit var defaultCityName : String

    private val defaultCity = LatLng(55.0, 37.0)

    private val callback = OnMapReadyCallback { googleMap ->
        initializeMap(googleMap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        defaultCityName = getString(R.string.default_city)
    }

    private fun initializeMap(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true

        val geocoder = Geocoder(requireContext(), Locale("ru"))
        val marker = checkLocation(geocoder, location)
        handleLocationCheck(marker)
        addMarkerOnMap(marker,location)
        moveCameraToLocation(marker)
    }

    private fun moveCameraToLocation(marker: LatLng) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 5f))
    }

    private fun addMarkerOnMap(marker: LatLng, location: String?) {
        map.addMarker(MarkerOptions().position(marker).title(location))
    }

    private fun handleLocationCheck(marker: LatLng) {
        if (marker == defaultCity) {
            Toast.makeText(requireContext(),
                getString(R.string.not_found_birth_city_geoposition),
                Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBirthPlaceMapBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun checkLocation (geocoder: Geocoder, location : String?): LatLng {
        Log.d("@@@", "Checking location for: $location")
        if(location.isNullOrEmpty()) {
            return defaultCity
        } else {
            return try {
                val searchResult =
                    checkGeoResult(geocoder, geocoder.getFromLocationName(location, 1))
                Log.d("@@@", "Geocoder results: $searchResult")
                if (searchResult.isNullOrEmpty()) {
                    defaultCity
                } else {
                    val lat = searchResult[0].latitude
                    val long= searchResult[0].longitude
                    LatLng(lat, long)
                }
            } catch (e: Exception) {
                defaultCity
            }
        }
    }

    private fun checkGeoResult (geocoder: Geocoder, searchResult: List<Address>?): List<Address> {
        return searchResult ?: geocoder.getFromLocationName(defaultCityName, 1).orEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}