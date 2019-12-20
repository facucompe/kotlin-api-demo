package com.example.demo.resolvers

import com.example.demo.enumerators.Genre
import com.example.demo.models.Book
import com.example.demo.repositories.BookRepository
import com.example.demo.responses.bookById.BookById
import com.example.demo.responses.createBook.CreateBook
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.graphql.spring.boot.test.GraphQLResponse
import com.graphql.spring.boot.test.GraphQLTestTemplate
import graphql.Assert
import junit.framework.Assert.assertNull
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.junit4.SpringRunner
import java.io.IOException

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BookResolverTest {

    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var graphQLTestTemplate: GraphQLTestTemplate

    private lateinit var book: Book
    private lateinit var objectMapper: ObjectMapper

    @Before
    fun setUp() {
        objectMapper = ObjectMapper().apply {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
        book = Book("1984", 250, Genre.DRAMA).let {
            bookRepository.save(it)
        }
    }

    @After
    fun after() {
        bookRepository.delete(book)
    }

    @Test
    fun bookByIdTest() {
        val rootNode = objectMapper.createObjectNode().apply {
            put("id", book.id)
        }

        val findResponse = graphQLTestTemplate
                .perform("queries/bookById.graphqls", rootNode)
        val bookById = objectMapper.readValue(findResponse.rawResponse.body, BookById::class.java)

        Assert.assertNotNull(findResponse)
        TestCase.assertTrue(findResponse.isOk)
        assert(bookById.data?.bookById?.id!!.equals(book.id))
        assert(bookById.data?.bookById?.name.equals(book.name))
        assertNull(bookById?.data?.bookById?.genre)
    }

    @Test
    fun bookByIdNotFound() {
        val rootNode = objectMapper.createObjectNode().apply {
            put("id", 99)
        }

        val findResponse = graphQLTestTemplate
                .perform("queries/bookById.graphqls", rootNode)
        val bookById = objectMapper.readValue(findResponse.rawResponse.body, BookById::class.java)

        Assert.assertNotNull(findResponse)
        TestCase.assertTrue(findResponse.isOk)
        assertNull(bookById.data?.bookById)
        assert(bookById?.errors?.first()?.message.equals("Exception while fetching data (/bookById) : Book not found"))
    }

    @Test
    fun createBookTest() {
        val rootNode = objectMapper.createObjectNode().apply {
            put("name", "Brave new world")
            put("genre", Genre.DRAMA.name)
            put("pageCount", 123)
        }

        val findResponse = graphQLTestTemplate
                .perform("queries/createBook.graphqls", rootNode)
        val createBook = objectMapper.readValue(findResponse.rawResponse.body, CreateBook::class.java)

        Assert.assertNotNull(findResponse)
        TestCase.assertTrue(findResponse.isOk)
        assert(createBook.data?.createBook?.name.equals("Brave new world"))
        assertNull(createBook.data?.createBook?.genre)
    }
}