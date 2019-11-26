package com.example.demo.subscriptions

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver
import com.example.demo.enumerators.Genre
import com.example.demo.models.Book
import com.example.demo.services.BookService
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookSubscription : GraphQLSubscriptionResolver {

    @Autowired
    lateinit var bookService: BookService

    fun bookCreationSubscription() : Publisher<Book> {
        return Flowable.fromArray(
                Book("hola", 123, Genre.DRAMA, null, 1)
        )
    }
}