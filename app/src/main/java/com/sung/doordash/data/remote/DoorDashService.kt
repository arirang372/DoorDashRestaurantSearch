package com.sung.doordash.data.remote

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DoorDashService {

    @GET("/v1/store_feed/")
    suspend fun getRestaurants(
        @Query("lat") latitude: Double, @Query("lng") longitude: Double,
        @Query("offset") offset: Int, @Query("limit") limit: Int
    ): SearchRestaurantsResponse

    @GET("/v2/restaurant/{restaurantId}")
    suspend fun getRestaurantDetails(@Path("restaurantId") restaurantId: Int): RestaurantDetailsResponse

    companion object {
        private const val BASE_URL = "https://api.doordash.com/"

        fun create(): DoorDashService {
            val logger = HttpLoggingInterceptor { Log.d("API", it) }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(logger)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DoorDashService::class.java)
        }
    }
}