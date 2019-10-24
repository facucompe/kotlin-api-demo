package com.example.demo.controllers

import com.example.demo.enumerators.Genre
import com.example.demo.models.Book
import com.example.demo.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController {

    @Autowired
    lateinit var bookRepository: BookRepository

    @GetMapping("/{id}")
    fun show(
            @PathVariable("id") id : Int
    ): Book {
        bookRepository.save(Book("asdf", 123, Genre.DRAMA))
        return bookRepository.findById(id).get()
    }

    @GetMapping
    fun index(): MutableList<Book> {
        return bookRepository.findAll()
    }
}
