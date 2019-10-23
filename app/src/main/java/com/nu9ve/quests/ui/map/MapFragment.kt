package com.nu9ve.quests.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nu9ve.quests.R
import com.nu9ve.quests.databinding.FragmentMapBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment: DaggerFragment(), OnMapReadyCallback {

//    @Inject
//    lateinit var loginViewModel: LoginViewModel

    private lateinit var viewDataBinding: FragmentMapBinding
    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private val LOCATION_PERMISSION_REQUEST_CODE = 40852

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewDataBinding = FragmentMapBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvents()
        setObservers()

        (activity as AppCompatActivity).supportActionBar?.hide()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)


    }

    private fun requestPermissions(){
        if ((ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) || ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            mFusedLocationClient.lastLocation.addOnCompleteListener{
                it.result?.let {location ->
                    mMap.addMarker(MarkerOptions().position(LatLng(location.latitude, location.longitude)))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 15f))
                }
            }
        }
    }

    private fun setEvents(){
        mapFab.setOnClickListener {
            findNavController().navigate(R.id.goToContactListFragment)
        }
    }

    private fun setObservers(){
//        loginViewModel.run {
//            correctLoginEvent.observe(viewLifecycleOwner, Observer { event ->
//                event.getContentIfNotHandled()?.let {
//                    if(it){
//                        findNavController().navigate(R.id.goToTutorialFragment)
//                    } else {
//                        Snackbar
//                            .make(loginLayout, "Tu no eres mi amo", Snackbar.LENGTH_LONG)
//                            .withColor(ContextCompat.getColor(activity!!, R.color.warningRed))
//                            .show()
//                    }
//                }
//            })
//        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapView.onResume()
        mMap = googleMap
        mMap.run {

            setOnMapLoadedCallback {
                requestPermissions()
            }

            setOnMarkerClickListener {
                findNavController().navigate(R.id.goToQuestDetailsFragment)
                true
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    mFusedLocationClient.lastLocation.addOnCompleteListener{
                        it.result?.let {location ->
                            mMap.addMarker(MarkerOptions().position(LatLng(location.latitude, location.longitude)))
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 15f))
                        }
                    }
                } else {
                    Toast.makeText(activity!!, "la cagaste", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
               Toast.makeText(activity!!, "no paso nada", Toast.LENGTH_SHORT).show()
            }
        }
    }

}