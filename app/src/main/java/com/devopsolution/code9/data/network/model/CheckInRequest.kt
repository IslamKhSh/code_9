package com.devopsolution.code9.data.network.model


import com.google.gson.annotations.SerializedName

data class CheckInRequest(
    @SerializedName("shopId")
    val shopId: Int,
    @SerializedName("userId")
    val userId: String
)