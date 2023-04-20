package com.example.rx.issue.service

import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap

@Component
class CoroutineCacheManager<T> {
    private val localCache = ConcurrentHashMap<String, CacheWrapper<T>>()

    suspend fun awaitPut(key: String, value: T, ttl: Duration) {
        localCache[key] = CacheWrapper(cached = value, Instant.now().plusMillis(ttl.toMillis()))
    }

    fun awaitEvict(token: String) {
        localCache.remove(key = token)
    }


    data class CacheWrapper<T>(val cached: T, val ttl: Instant)
}