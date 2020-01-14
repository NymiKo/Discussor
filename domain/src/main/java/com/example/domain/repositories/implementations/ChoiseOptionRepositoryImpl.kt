package com.example.domain.repositories.implementations

import com.example.data.remote.models.ChoiseOptionApi
import com.example.data.remote.providers.ChoiseOptionProviderImpl
import com.example.domain.repositories.ChoiseOptionRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception

class ChoiseOptionRepositoryImpl: ChoiseOptionRepository {

    override suspend fun choiseOfOption(idTheme: Int, vote_for: String, vote_against: String): Deferred<ChoiseOptionApi> {
        val choiseOptionProviderImpl = ChoiseOptionProviderImpl()

        try {
            val choiseOption = choiseOptionProviderImpl.sendSelection(
                idTheme = idTheme, vote_for = vote_for, vote_against = vote_against).await()

            return GlobalScope.async {
                choiseOption
            }
        } catch (e: Exception) {
            return GlobalScope.async { error(e) }
        }
    }
}