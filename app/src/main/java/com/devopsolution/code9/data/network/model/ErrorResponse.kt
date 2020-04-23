package com.devopsolution.code9.data.network.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val errors: String? = null,
    @SerializedName("message")
    val message: String?
)