package com.example.rx

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(private val handler: HelloHandler) {
    @Bean
    fun helloRouter(): RouterFunction<ServerResponse> =
        route()
            .GET("/", handler::sayHello)
            .build()
    @Bean
    fun userRouter(handler: UserHandler): RouterFunction<ServerResponse> =
        router {
            "/users".nest {
                GET("/{id}", handler::getUser)
                GET("", handler::getAll)
            }
        }
}