package com.example.rx.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

data class Book(val id: Int, val name: String, val price: Int)

@Service
class BookService {

    val books = mutableListOf<Book>(
        Book(id =1, name = "코틀린 인 액션", price = 10000),
        Book(id =2, name = "HTTP 완벽 가이드", price = 20000),
        Book(id =3, name = "자바에서 코틀린으로", price = 50000),
    )

    fun getAll(): Flux<Book> {
        return Flux.fromIterable(books)
    }

    fun get(id: Int): Mono<Book> {
        return Mono.justOrEmpty(books.find {
            it.id == id
        })
    }

}
