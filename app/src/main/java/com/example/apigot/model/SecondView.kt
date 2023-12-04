package com.example.apigot.model

import com.google.gson.annotations.SerializedName

data class SecondView(
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("family")
    val family: String?,
    @SerializedName("houseFlag")
    val houseFlag: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?
)
