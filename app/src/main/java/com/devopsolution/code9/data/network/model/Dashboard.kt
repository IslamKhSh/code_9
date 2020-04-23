package com.devopsolution.code9.data.network.model


import com.google.gson.annotations.SerializedName

data class Dashboard(
    @SerializedName("numberOfUsersIn")
    val numberOfUsersIn: Int?,
    @SerializedName("status")
    val status: Int
)