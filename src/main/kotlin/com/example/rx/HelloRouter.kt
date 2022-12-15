package com.example.rx

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class Router(private val handler: HelloHandler) {
    @Bean
    fun helloRouter(): RouterFunction<ServerResponse> =
        route()
            .GET("/", handler::sayHello)
            .build()
}