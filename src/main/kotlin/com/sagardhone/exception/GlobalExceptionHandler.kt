package com.sagardhone.exception

import exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorResponse(val statusCode: Int, val message: String)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleCustomException(ex: CustomException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.statusCode, ex.message ?: "Unknown error")
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse)
    }
}
