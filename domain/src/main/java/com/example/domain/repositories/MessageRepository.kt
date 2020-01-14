package com.example.domain.repositories

import com.example.data.remote.models.MessageApi
import kotlinx.coroutines.Deferred

interface MessageRepository {
    suspend fun sendMessage(fromWhom: String, toWhom: String, message: String): Deferred<MessageApi>
}