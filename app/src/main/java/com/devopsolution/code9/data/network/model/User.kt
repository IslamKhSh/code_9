package com.devopsolution.code9.data.network.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("dateOfBirth")
    val age: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("id")
    val id: String,
    @SerializedName(value = "idNumber", alternate = ["licenseNumber"])
    val idNumber: String,
    @SerializedName("imagePath")
    val imagePath: String?,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("latitude")
    val lat : Double?,
    @SerializedName("longitude")
    val lng : Double?,
    @SerializedName("distance")
    val distance : Double?
)