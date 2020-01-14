package com.example.data.remote.services

import com.example.data.remote.models.ChoiseOptionApi
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ChoiseOptionService {
    @GET("/send_choice_option.php")
    fun sendSelection(@Query("id_theme") idTheme: Int,
                      @Query("vote_for") vote_for: String,
                      @Query("vote_against") vote_against: String): Deferred<ChoiseOptionApi>
}