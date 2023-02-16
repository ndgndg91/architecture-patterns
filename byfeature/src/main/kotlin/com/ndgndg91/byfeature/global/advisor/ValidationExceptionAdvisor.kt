package com.ndgndg91.byfeature.global.advisor

import com.ndgndg91.byfeature.global.exception.internal.ValidationException
import com.ndgndg91.byfeature.global.protocol.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidationExceptionAdvisor {

    @ExceptionHandler(ValidationException::class)
    fun validationException(e: ValidationException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(e.statusCode)
            .body(ErrorResponse(e))
    }
}