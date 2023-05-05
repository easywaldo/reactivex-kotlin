package com.example.rx.book.repository

import com.example.rx.book.domain.model.BookEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface BookEntityRepository: ReactiveCrudRepository<BookEntity, Long> {
    fun findByName(name: String): Mono<BookEntity>
}