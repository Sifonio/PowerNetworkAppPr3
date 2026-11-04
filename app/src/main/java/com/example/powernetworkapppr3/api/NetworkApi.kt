package com.example.powernetworkapppr3.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Substation(val id: Int, val name: String, val capacity: Double)

interface NetworkApiService {
    @GET("substations") // Викликаємо неіснуючий ресурс
    suspend fun getSubstations(): List<Substation>
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5156/"
    val api: NetworkApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkApiService::class.java)
    }
}