package com.example.demo.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.demo.exceptions.ResourceNotFound
import com.example.demo.models.Author
import com.example.demo.repositories.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthorResolver : GraphQLQueryResolver {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    fun authorById(id: Int): Author? {
        return authorRepository.findById(id).orElseThrow { ResourceNotFound("Author not found", id) }
    }
}
