package com.example.estel.greenspacesmanagement.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MaintenanceTasks(val type: String, val longitude: String, val latitude: String, val address: String, val description : String) : Parcelable