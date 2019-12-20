package com.example.demo.exceptions

import graphql.ErrorType
import graphql.language.SourceLocation

class ExternalServiceError(val errorMessage: String) : BaseException() {

    override fun errorMessage() = errorMessage

    override fun getErrorType() = ErrorType.DataFetchingException

    override fun getLocations() = null
}
