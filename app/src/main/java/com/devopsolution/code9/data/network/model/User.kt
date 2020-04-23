package com.devopsolution.code9.data.network.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("dateOfBirth")
    val age: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("id")
    val id: String,
    @SerializedName(value = "idNumber", alternate = ["licenseNumber"])
    val idNumber: String,
    @SerializedName("imagePath")
    val imagePath: String? = null,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("latitude")
    val lat: Double? = null,
    @SerializedName("longitude")
    val lng: Double? = null,
    @SerializedName("distance")
    val distance: Double? = null
)