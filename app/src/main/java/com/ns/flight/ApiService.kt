package com.ns.flight

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("developer-test-flights-list.json")
    suspend fun flights(@Query("alt") alt: String,
                @Query("token") token: String): List<Flight>
}