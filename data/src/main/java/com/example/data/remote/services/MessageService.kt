package com.example.data.remote.services

import com.example.data.remote.models.MessageApi
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MessageService {
    @GET("/fcm_service.php")
    fun sendMessage(@Query("from_whom") fromWhom: String,
                      @Query("to_whom") toWhom: String,
                      @Query("message") message: String): Deferred<MessageApi>
}