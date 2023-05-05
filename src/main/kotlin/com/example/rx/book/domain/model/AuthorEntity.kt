package com.example.rx.book.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "author")
data class AuthorEntity (
        @Id
        val id: Long? = null,
        @Column
        val name: String,
        @Column
        val country: String
)