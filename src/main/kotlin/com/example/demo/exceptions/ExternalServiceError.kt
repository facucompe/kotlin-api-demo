package com.example.demo.exceptions

import graphql.ErrorType
import graphql.language.SourceLocation

class ExternalServiceError(val errorMessage: String) : BaseException() {

    override fun errorMessage(): String = errorMessage

    override fun getErrorType(): ErrorType = ErrorType.DataFetchingException

    override fun getLocations(): MutableList<SourceLocation>? = null
}
