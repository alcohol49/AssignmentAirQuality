package com.mch.ubiquiti.data

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("aqx_p_432")
    suspend fun fetch(
        @Query("limit") limit: Int = 1000,
        @Query("api_key") apiKey: String = "9be7b239-557b-4c10-9775-78cadfc555e9",
        @Query("sort") sort: String = "ImportDate desc",
        @Query("format") format: String = "json"
    ): Response

    data class Response(
        @field:SerializedName("records") val records: List<Record>
    )


    companion object {
        private const val BASE_URL = "https://data.epa.gov.tw/api/v1/"

        fun create(): WebService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WebService::class.java)
        }
    }
}