package com.example.rx

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@ConfigurationPropertiesScan
@SpringBootApplication
class RxApplication

@Bean
fun init(connectionFactory: ConnectionFactory) =
	ConnectionFactoryInitializer().apply {
		setConnectionFactory(connectionFactory)
		setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
	}

fun main(args: Array<String>) {
	runApplication<RxApplication>(*args)
}
