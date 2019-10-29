package com.example.demo.controllers

import com.example.demo.DTO.BookInput
import com.example.demo.models.Book
import com.example.demo.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import org.modelmapper.ModelMapper


@RestController
@RequestMapping("/books")
class BookController {

    @Autowired
    lateinit var bookRepository: BookRepository

    @GetMapping("/{id}")
    fun show(
            @PathVariable("id") id : Int
    ): Book {
        return bookRepository.findById(id).get()
    }

    @GetMapping
    fun index(): MutableList<Book> {
        return bookRepository.findAll()
    }

    @PostMapping
    fun create(@Valid
               @NotNull
               @RequestBody
               bookInput: BookInput) : Book? {

        val book = ModelMapper().map(bookInput, Book::class.java)
        bookRepository.save(book)
        return book
    }
}
