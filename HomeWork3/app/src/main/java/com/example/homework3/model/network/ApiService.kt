package com.example.homework3.model.network


import com.example.homework3.model.data.Exercise
import com.example.homework3.model.data.Training
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {
    @Headers("Authorization: $API_KEY")
    @GET("313/list+of+all+exercise")
    suspend fun getExercisesList(
//        @Query("type") type: String
    ): List<Exercise>

    @Headers("Authorization: $API_KEY")
    @GET("311/list+of+target+muscles")
    suspend fun getTrainingsList(
    ): List<String>

    companion object {

        private const val API_KEY = "Bearer " + "490|ZjEtkOJDBiW5dnoafTcZhLucIyTMUZBJAZjuV49q"
        private const val BASE_URL = "https://zylalabs.com/api/392/exercise+database+api/"

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