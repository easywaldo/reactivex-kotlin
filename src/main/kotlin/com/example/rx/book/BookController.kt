package com.example.rx.book

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

@RestController
class BookController(
    private val bookService: BookService,
    private val bookRepository: BookEntityRepository,
) {
    @GetMapping("/books")
    fun getAll(): Flux<Book> {
        return bookService.getAll()
    }

    @GetMapping("/books/{id}")
    fun get(@PathVariable id: Int) : Mono<Book> {
        return bookService.get(id)
    }

    @PostMapping("/books")
    fun add(@RequestBody addBookCommand: Map<String, Any>) : Mono<Book> {
        return bookService.add(addBookCommand)
    }

    @DeleteMapping("/books/{id}")
    fun delete(@PathVariable id: Int): Mono<Void> {
        return bookService.delete(id)
    }



    // R2DBC URI
    @GetMapping("/r2/books/{name}")
    fun getByName(@PathVariable name: String): Mono<BookEntity> {
        return bookRepository.findByName(name)
    }

    @PostMapping("/r2/books")
    fun create(@RequestBody map: Map<String, Any>): Mono<BookEntity> {
        val bookEntity = BookEntity(
            name=map["name"].toString(),
            price = map["price"] as Int
        )
        return bookRepository.save(bookEntity)
    }

    @GetMapping("/r2/books")
    fun getAllByR2DBC(): Flux<BookEntity> {
        return bookRepository.findAll()
    }

}