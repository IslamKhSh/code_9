package com.devopsolution.code9.data.network.model


import com.google.gson.annotations.SerializedName

data class GeneralResponse<T>(
    @SerializedName("data")
    val `data`: T,
    @SerializedName("errors")
    val errors: List<String>,
    @SerializedName("isSuccess")
    val isSuccess: Boolean
)