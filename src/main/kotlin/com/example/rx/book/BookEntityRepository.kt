package com.example.rx.book

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface BookEntityRepository: ReactiveCrudRepository<BookEntity, Long> {
    fun findByName(name: String): Mono<BookEntity>
}