package com.example.apigot.network

import com.example.apigot.model.PrincipalView
import com.example.apigot.model.SecondView
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GotAPI {

    @GET
    fun getGot(
        @Url url: String?
    ): Call<ArrayList <PrincipalView>>

    @GET("characters/character_detail")
    fun getCharacterDetail(
        @Query("id") id: String?
    ): Call<SecondView>

    @GET("characters/character_detail/{id}")
    fun getCharacterDetailApiary(
        @Path("id") id: String?
    ): Call<SecondView>
}