package com.example.rx.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.atomic.AtomicInteger

data class Book(val id: Int, val name: String, val price: Int)

@Service
class BookService {

    private final val nextId = AtomicInteger(0)
    val books = mutableListOf<Book>(
        Book(id =nextId.incrementAndGet(), name = "코틀린 인 액션", price = 10000),
        Book(id =nextId.incrementAndGet(), name = "HTTP 완벽 가이드", price = 20000),
        Book(id =nextId.incrementAndGet(), name = "자바에서 코틀린으로", price = 50000),
    )

    fun getAll(): Flux<Book> {
        return Flux.fromIterable(books)
    }

    fun get(id: Int): Mono<Book> {
        return Mono.justOrEmpty(books.find {
            it.id == id
        })
    }

    fun add(addBookCommand: Map<String, Any>): Mono<Book> {
        return Mono.just(addBookCommand)
            .map {
                map -> val book = Book(
                    id=nextId.incrementAndGet(),
                    name=map["name"].toString(),
                    price=map["price"] as Int
                )
                books.add(book)
                book
            }
    }

    fun delete(id: Int): Mono<Void> {
        return Mono.justOrEmpty(books.find { it.id == id })
            .map { books.remove(it) }
            .then()
    }

}
