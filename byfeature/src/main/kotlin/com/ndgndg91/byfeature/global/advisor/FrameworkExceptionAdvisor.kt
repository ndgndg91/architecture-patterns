package com.ndgndg91.byfeature.global.advisor

import com.ndgndg91.byfeature.global.exception.ErrorCode
import com.ndgndg91.byfeature.global.protocol.ErrorDetail
import com.ndgndg91.byfeature.global.protocol.ErrorResponse
import com.ndgndg91.byfeature.global.protocol.MetaResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class FrameworkExceptionAdvisor {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest()
            .body(ErrorResponse(ErrorDetail("VALIDATION", e.message?: ""), MetaResponse(ErrorCode.INVALID_VALUE.code)))
    }
}