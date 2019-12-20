package com.example.demo.resolvers

import com.example.demo.models.Author
import com.example.demo.repositories.AuthorRepository
import com.example.demo.responses.authorById.AuthorById
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.graphql.spring.boot.test.GraphQLResponse
import com.graphql.spring.boot.test.GraphQLTestTemplate
import junit.framework.Assert.assertNull
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorResolverTest {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var graphQLTestTemplate: GraphQLTestTemplate

    private lateinit var author: Author
    private lateinit var objectMapper: ObjectMapper

    @Before
    fun setUp() {
        objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        author = Author("George", "Orwell", null, 1)
        authorRepository.save(author)
    }

    @After
    fun after() {
        authorRepository.delete(author)
    }

    @Test
    fun authorById() {
        val rootNode: ObjectNode = objectMapper.createObjectNode()
        rootNode.put("id", author.id)

        val findResponse: GraphQLResponse = graphQLTestTemplate
                .perform("queries/authorById.graphqls", rootNode)
        val authorById: AuthorById = objectMapper.readValue(findResponse.rawResponse.body, AuthorById::class.java)

        Assert.assertNotNull(findResponse)
        TestCase.assertTrue(findResponse.isOk)
        assert(authorById.data?.authorById?.id!!.equals(author.id))
        assert(authorById.data?.authorById?.firstName.equals(author.firstName))
        assertNull(authorById?.data?.authorById?.lastName)
    }

    @Test
    fun authorByIdNotFound() {
        val rootNode: ObjectNode = objectMapper.createObjectNode()
        rootNode.put("id", 99)

        val findResponse: GraphQLResponse = graphQLTestTemplate
                .perform("queries/authorById.graphqls", rootNode)
        val authorById: AuthorById = objectMapper.readValue(findResponse.rawResponse.body, AuthorById::class.java)

        graphql.Assert.assertNotNull(findResponse)
        TestCase.assertTrue(findResponse.isOk)
        assertNull(authorById.data?.authorById)
        assert(authorById?.errors?.first()?.message.equals("Exception while fetching data (/authorById) : Author not found"))
    }
}
