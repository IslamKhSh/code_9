package com.devopsolution.code9.data.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Shop(
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imagePath")
    val imagePath: String?,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("licenseNumber")
    val licenseNumber: String,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String?
) : Serializable