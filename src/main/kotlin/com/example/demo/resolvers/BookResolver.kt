package com.example.demo.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.demo.enumerators.Genre
import com.example.demo.models.Book
import com.example.demo.repositories.BookRepository
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookResolver : GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    lateinit var bookRepository: BookRepository

    fun bookById(id: Int): Book? {
        return bookRepository.findById(id).get()
    }

    // TODO: Input types are not working. However information can be parsed from dataFetchingEnvironment
    fun createBook(name: String?, pageCount: Int?, genre: Genre?, dataFetchingEnvironment: DataFetchingEnvironment?): Book {
        val book = Book(name, pageCount, genre)
        return bookRepository.save(book)
    }
}