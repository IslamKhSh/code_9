package com.devopsolution.code9.data.network.model


import com.google.gson.annotations.SerializedName

data class DashboardRequest(
    @SerializedName("id")
    val id: String?,
    @SerializedName("userType")
    val userType: Int?
)