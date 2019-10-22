package com.example.estel.greenspacesmanagement.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.estel.greenspacesmanagement.Google.MapsActivity
import com.example.estel.greenspacesmanagement.R
import com.example.estel.greenspacesmanagement.model.MaintenanceTasks
import com.example.estel.greenspacesmanagement.model.TaskDb
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_detail_maintenance_task.*

class DetailMaintenanceTask : AppCompatActivity() {

    val taskDb = TaskDb()
    var latlng: LatLng? = null
    var task: MaintenanceTasks? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_maintenance_task)

        //toolbar
        toolbar_detail.title = ""
        setSupportActionBar(toolbar_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //display the informations about the task
        val bundle: Bundle? = intent.extras
        bundle?.let {
            bundle.apply {
                task = getParcelable("task")
                if (task != null) {
                    address_detail.text = task?.address
                    description_detail.text = task?.description
                    type_detail.text = task?.type

                    latlng = LatLng(task!!.latitude.toDouble(),task!!.longitude.toDouble())
                }

            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //delete the task only if the person confirms
    private fun deleteTask() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setMessage("Voulez-vous vraiment supprimer cette tâche ?")
        //when it's yes -> delete the task and go back to main activity
        alertDialog.setPositiveButton("Oui") {
            dialogInterface, i ->
            taskDb.deleteTask(this!!.task!!)
            val intent = Intent(this, ListMaintenanceTasks::class.java)
            startActivity(intent)
        }
        //when it's no -> do nothing
        alertDialog.setNegativeButton("Non") {
            dialogInterface, i-> dialogInterface.cancel()
        }
        alertDialog.show()


    }

    //open a map with the GPS position of the task
    private fun positionTask() {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("latlng",latlng)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    //manages the click on the actionbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete_task -> {
                deleteTask()
                return true
            }
            R.id.action_position_task -> {
                positionTask()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}