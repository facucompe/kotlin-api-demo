package com.example.demo.exceptions

import graphql.ErrorType
import graphql.language.SourceLocation

class ResourceNotFound(val errorMessage: String, val id: Int) : BaseException() {

    private val extensions : HashMap<String, Any> = HashMap()

    override fun errorMessage() = errorMessage

    override fun getErrorType() = ErrorType.DataFetchingException

    override fun getLocations() = null

    override fun getExtensions(): MutableMap<String, Any> {
        extensions.put("Missing record id", id)
        return extensions
    }
}