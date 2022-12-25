package com.example.rx.book

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface BookEntityRepository: ReactiveCrudRepository<Book, Long> {
    fun findByName(name: String): Mono<BookEntity>
}