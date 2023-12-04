package com.example.apigot.model

import com.google.gson.annotations.SerializedName

data class PrincipalView(
    @SerializedName("id")
    val id: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?
)
