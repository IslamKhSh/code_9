package com.devopsolution.code9.data.network.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("shops")
    val shops: List<Shop>,
    @SerializedName("iconPath")
    val icon: String?
)