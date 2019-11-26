package com.example.demo.services

import com.example.demo.models.Book
import org.springframework.stereotype.Service
import io.reactivex.Flowable;

@Service
class BookService {
    private final var publisher : Flowable<Book>? = null

    fun getCreation() : Flowable<Book>? {
        return publisher
    }
}