package com.example.estel.greenspacesmanagement.view
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.estel.greenspacesmanagement.model.MaintenanceTasks
import com.example.estel.greenspacesmanagement.model.TaskDb
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_add_maintenance_task.*
import org.jetbrains.anko.toast
import android.location.Address
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.example.estel.greenspacesmanagement.Google.MapsActivity
import com.example.estel.greenspacesmanagement.R
import java.io.IOException

class AddMaintenanceTask : View.OnClickListener, AppCompatActivity() {

    val taskDb = TaskDb()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_maintenance_task)

        //Toolbars
        collapsing_toolbar.title = "Votre problème"
        setSupportActionBar(toolbar)
        //Display Button back on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this,
                R.array.type_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        localisation_button.setOnClickListener(this)

        //submit button onclick
        submit_btn.setOnClickListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(view : View) {
        if (view == localisation_button) {
            giveLocalisationAndAddress()
        }
        if (view == submit_btn) {
            if (editTextLat.text.isNotEmpty() && editTextLong.text.toString().isNotEmpty()) {
                val newTask = MaintenanceTasks(spinner.selectedItem.toString(), editTextLong.text.toString(),
                        editTextLat.text.toString(), editTextAddress.text.toString(), editTextDescription.text.toString())
                taskDb.saveTask(newTask)
                toast("ok")
                val intent = Intent(this, ListMaintenanceTasks::class.java)
                startActivity(intent)
            }
            else {
                toast("Position GPS obligatoire !")
            }
        }

    }

    @SuppressLint("MissingPermission")
    private fun giveLocalisationAndAddress() {
        setUpMap()
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            editTextLat.setText(location?.latitude.toString())
            editTextLong.setText(location?.longitude.toString())
            val latLngaddress = LatLng(location?.latitude.toString().toDouble(), location?.longitude.toString().toDouble())
            editTextAddress.setText(getAddress(latLngaddress))
        }

    }

    private fun getAddress(latLng: LatLng): String {
        val geocoder = Geocoder(this)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                /*for (i in 0 until address.maxAddressLineIndex) {*/
                    addressText += /*if (i == 0) */address.getAddressLine(0) /*else "\n" + address.getAddressLine(i)*/


            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }

        return addressText
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MapsActivity.LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }
}
