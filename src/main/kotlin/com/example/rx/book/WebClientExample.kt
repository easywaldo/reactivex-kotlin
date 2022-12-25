package com.example.rx.book

import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class WebClientExample {
    val url = "http://localhost:8088/books"
    val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/books/block")
    fun getBooksBlockingWay(): List<Book> {
        log.info("Start RestTemplate")
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Book>>(){} )
        val result = response.body!!
        log.info("result : {}", result)
        log.info("Finish RestTemplated")
        return result
    }
}