package com.example.data.remote.providers

import com.example.data.remote.helpers.RetrofitFactory
import com.example.data.remote.models.ChoiseOptionApi
import com.example.data.remote.services.ChoiseOptionService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class ChoiseOptionProviderImpl {
    @UnstableDefault
    fun sendSelection(idTheme: Int, vote_for: String, vote_against: String): Deferred<ChoiseOptionApi> {
        return RetrofitFactory.getRetrofitClient().create(ChoiseOptionService::class.java).sendSelection(
            idTheme = idTheme, vote_for = vote_for, vote_against = vote_against)
    }
}