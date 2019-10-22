package com.example.estel.greenspacesmanagement.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.estel.greenspacesmanagement.App
import org.jetbrains.anko.db.*

class TaskDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION){

    companion object {
        val DB_NAME = "task.db"
        val DB_VERSION = 1
        val instance by lazy { TaskDbHelper()}
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Tables.MaintenanceTasksTable.NAME, true, Tables.MaintenanceTasksTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Tables.MaintenanceTasksTable.TYPE to TEXT, Tables.MaintenanceTasksTable.LATITUDE to TEXT, Tables.MaintenanceTasksTable.LONGITUDE
                to TEXT, Tables.MaintenanceTasksTable.ADDRESS to TEXT, Tables.MaintenanceTasksTable.DESCRIPTION to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Tables.MaintenanceTasksTable.NAME, true)
        onCreate(db)

    }
}