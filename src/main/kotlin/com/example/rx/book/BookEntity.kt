package com.example.rx.book

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "book_entity")
data class BookEntity(
    @Id
    val id: Long? = null,
    @Column
    val name: String,
    @Column
    val price: Int,
)