package com.example.estel.greenspacesmanagement.model

import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.insert

class TaskDb(private val dbHelper: TaskDbHelper = TaskDbHelper.instance) {

    fun requestTask() = dbHelper.use {
        select(Tables.MaintenanceTasksTable.NAME, Tables.MaintenanceTasksTable.TYPE, Tables.MaintenanceTasksTable.LONGITUDE, Tables.MaintenanceTasksTable.LATITUDE,
                Tables.MaintenanceTasksTable.ADDRESS, Tables.MaintenanceTasksTable.DESCRIPTION).parseList(classParser<MaintenanceTasks>())
    }

    fun saveTask(task: MaintenanceTasks) = dbHelper.use {
        insert(Tables.MaintenanceTasksTable.NAME,  Tables.MaintenanceTasksTable.TYPE to task.type, Tables.MaintenanceTasksTable.LONGITUDE
        to task.longitude, Tables.MaintenanceTasksTable.LATITUDE to task.latitude, Tables.MaintenanceTasksTable.ADDRESS to task.address,
                Tables.MaintenanceTasksTable.DESCRIPTION to task.description)
    }

    fun saveTasks(taskList: List<MaintenanceTasks>) {
        for (t in taskList)
            saveTask(t)
    }

    fun deleteTask(task: MaintenanceTasks) = dbHelper.use {
        /*delete(Tables.MaintenanceTasksTable.NAME, whereClause = "task = {task}",
                args = "task" to task)*/
    }

    fun deleteTasks() = dbHelper.use {
        delete(Tables.MaintenanceTasksTable.NAME)
    }
}