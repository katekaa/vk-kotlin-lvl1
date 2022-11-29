package com.example.homework2.model.network


import com.example.homework2.model.data.BeerDT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("beers")
    suspend fun getBeersList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): List<BeerDT>

    companion object{
        private const val BASE_URL = "https://api.punkapi.com/v2/"

        fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().apply {
                addNetworkInterceptor(interceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(BASE_URL)
            }.build()

            return retrofit.create(ApiService::class.java)
        }
    }
}