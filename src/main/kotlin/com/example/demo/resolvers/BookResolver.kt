package com.example.demo.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.demo.DTO.BookInput
import com.example.demo.exceptions.ResourceNotFound
import com.example.demo.models.Book
import com.example.demo.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class BookResolver : GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    lateinit var bookRepository: BookRepository

    fun bookById(id: Int): Book? {
        return bookRepository.findById(id).orElseThrow { ResourceNotFound("Book not found", id) }
    }

    fun createBook(input: BookInput): Book {
        val book = Book(input.name, input.pageCount, input.genre)
        return bookRepository.save(book)
    }
}