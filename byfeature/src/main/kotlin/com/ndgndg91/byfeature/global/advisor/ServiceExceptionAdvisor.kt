package com.ndgndg91.byfeature.global.advisor

import com.ndgndg91.byfeature.global.exception.internal.ServiceException
import com.ndgndg91.byfeature.global.protocol.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ServiceExceptionAdvisor {

    @ExceptionHandler(ServiceException::class)
    fun serviceException(e: ServiceException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(e.statusCode)
            .body(ErrorResponse(e))
    }
}