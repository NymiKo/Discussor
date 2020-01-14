package com.example.data.remote.providers

import com.example.data.remote.helpers.RetrofitFactory
import com.example.data.remote.models.MessageApi
import com.example.data.remote.services.MessageService
import kotlinx.coroutines.Deferred
import kotlinx.serialization.UnstableDefault

class MessageProviderImpl {
    @UnstableDefault
    fun sendMessage(fromWhom: String, toWhom: String, message: String): Deferred<MessageApi> {
        return RetrofitFactory.getRetrofitClient().create(MessageService::class.java).sendMessage(fromWhom = fromWhom, toWhom = toWhom, message = message)
    }
}