package com.example.estel.greenspacesmanagement.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.estel.greenspacesmanagement.R
import com.example.estel.greenspacesmanagement.model.MaintenanceTasks
import com.example.estel.greenspacesmanagement.adapter.TaskAdapter
import com.example.estel.greenspacesmanagement.model.TaskDb
import kotlinx.android.synthetic.main.activity_list_maintenance_tasks.*
import org.jetbrains.anko.doAsync



class ListMaintenanceTasks : AppCompatActivity(),View.OnClickListener {

    val taskDb = TaskDb()
    val list = ArrayList<MaintenanceTasks>()
    val adapter = TaskAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_maintenance_tasks)
        toolbar2.title = ""
        setSupportActionBar(toolbar2)

        //add tasks in list and call adapter
        doAsync {
            val listFromDb = taskDb.requestTask()
            for (task in listFromDb) {
                list.add(task)
            }
        }
        setupRecyclerView(mytask_list_view)

        //add task button on click
        add_task_button.setOnClickListener(this)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }

    override fun onClick(view : View) {
        if (view == add_task_button) {
            val intent = Intent(this, AddMaintenanceTask::class.java)
            startActivity(intent)
        }
    }

    //function to fill the database with tasks
    private fun fillDB() {
        val tasksList = ArrayList<MaintenanceTasks>()
        val oneTask = MaintenanceTasks("Arbre à tailler","3.1454075","50.6108608","52 rue de Ticléni, Villeneuve d'Ascq","Au niveau du parking" +
                " de la résidence Gustave Eiffel, un arbre empêche de garer sa voiture à cause des branches trop basses.")
        val secondTask = MaintenanceTasks("Arbre à arbre","3.1454075","50.6108608","52 rue de Ticléni, Villeneuve d'Ascq","")
        tasksList.add(oneTask)
        tasksList.add(secondTask)
        tasksList.add(oneTask)
        tasksList.add(secondTask)
        tasksList.add(oneTask)
        tasksList.add(secondTask)
        tasksList.add(oneTask)
        taskDb.saveTasks(tasksList)
        mytask_list_view.adapter.notifyDataSetChanged()
    }

    //function to clean the database
    private fun cleanDB() {
        taskDb.deleteTasks()
        mytask_list_view.adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //manages the click on the actionbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings_fill -> {
                fillDB()
                return true
            }
            R.id.action_settings_clean -> {
                cleanDB()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        mytask_list_view.adapter.notifyDataSetChanged()

    }

}
