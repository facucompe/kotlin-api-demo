package com.example.demo.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.demo.models.Book
import com.example.demo.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookResolver : GraphQLQueryResolver {

    @Autowired
    lateinit var bookRepository: BookRepository

    fun bookById(id: Int): Book? {
        return bookRepository.findById(id).get()
    }
}