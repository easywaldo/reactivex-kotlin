package com.example.rx.book

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

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

    @PutMapping("/r2/books/{id}")
    @Transactional
    fun update(@PathVariable id: Long, @RequestBody map: Map<String, Any>): Mono<BookEntity> {
        val bookEntity = BookEntity(
            id = id, name = map["name"].toString(), price = map["price"] as Int)
        return bookRepository.findById(id).flatMap {
            b -> bookRepository.save(bookEntity)
        }

    }

}