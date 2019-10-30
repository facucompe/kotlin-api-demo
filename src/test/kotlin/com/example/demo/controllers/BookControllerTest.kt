package com.example.demo.controllers

import org.hamcrest.Matchers.`is`
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

import com.example.demo.enumerators.Genre
import com.example.demo.models.Book
import com.example.demo.repositories.BookRepository
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebAppConfiguration
@WebMvcTest(value = BookController::class, secure = false)
@RunWith(SpringRunner::class)
class BookControllerTest {

    @Autowired
    lateinit var mvc : MockMvc;

    @MockBean
    lateinit var bookRepository : BookRepository

    var book = Book("1984", 123, Genre.DRAMA, 1)

    @Test
    @Throws(Exception::class)
    fun bookShowTest() {
        given(bookRepository.findById(ArgumentMatchers.anyInt())).willReturn(Optional.of(book))

        mvc.perform(get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", `is`(book.name)))
                .andExpect(jsonPath("$.pageCount", `is`(book.pageCount)))
    }

    @Test
    @Throws(Exception::class)
    fun bookShowTest_NOT_FOUND() {
        given(bookRepository.findById(ArgumentMatchers.anyInt())).willThrow(NoSuchElementException::class.java)

        mvc.perform(get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound)
    }
}
