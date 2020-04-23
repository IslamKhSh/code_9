package com.devopsolution.code9.data.network.model


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("UserType")
    val userType: Int
)