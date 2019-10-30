package com.example.demo.controllers

import com.example.demo.DTO.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ErrorController {

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    internal fun onConstraintValidationException(e: NoSuchElementException?): ErrorResponse {
        return ErrorResponse("Resource not found", e?.message.toString())
    }
}
