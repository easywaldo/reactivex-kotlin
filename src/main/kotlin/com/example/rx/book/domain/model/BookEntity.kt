package com.example.rx.book.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.ManyToOne

@Table(name = "book_entity")
data class BookEntity(
    @Id
    val id: Long? = null,
    @Column
    val name: String,
    @Column
    val price: Int,
    @Column
    @ManyToOne
    val author: AuthorEntity? = null
)