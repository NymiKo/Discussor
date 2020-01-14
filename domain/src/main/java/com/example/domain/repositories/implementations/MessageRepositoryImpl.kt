package com.example.domain.repositories.implementations

import com.example.data.remote.models.MessageApi
import com.example.data.remote.providers.ChoiseOptionProviderImpl
import com.example.data.remote.providers.MessageProviderImpl
import com.example.domain.repositories.MessageRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception

class MessageRepositoryImpl: MessageRepository {

    @Suppress("EXPERIMENTAL_API_USAGE")
    override suspend fun sendMessage(fromWhom: String, toWhom: String, message: String): Deferred<MessageApi> {
        val messageProviderImpl = MessageProviderImpl()

        try {
            val messageProvider = messageProviderImpl.sendMessage(fromWhom = fromWhom, toWhom = toWhom, message = message).await()

            return GlobalScope.async {
                messageProvider
            }
        } catch (e: Exception) {
            return GlobalScope.async { error(e) }
        }
    }
}