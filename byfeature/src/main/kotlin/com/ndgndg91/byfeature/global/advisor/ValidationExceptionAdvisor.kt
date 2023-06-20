package com.ndgndg91.byfeature.global.advisor

import com.ndgndg91.byfeature.global.exception.ErrorCode
import com.ndgndg91.byfeature.global.protocol.ErrorDetail
import com.ndgndg91.byfeature.global.protocol.ErrorResponse
import com.ndgndg91.byfeature.global.protocol.MetaResponse
import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class ValidationExceptionAdvisor {

    private val log = LoggerFactory.getLogger(ValidationExceptionAdvisor::class.java)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest()
            .body(ErrorResponse(ErrorDetail("VALIDATION", e.message ?: ""), MetaResponse(ErrorCode.INVALID_VALUE.code)))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(e: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        e.constraintViolations.forEach {
            log.info("cv : {}", it)
        }
        return ResponseEntity.badRequest()
            .body(
                ErrorResponse(
                    ErrorDetail("VALIDATION", ""),
                    MetaResponse(ErrorCode.INVALID_VALUE.code)
                )
            )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        log.info("message : {}", e.message)
        log.info("parameter : {}", e.parameter)
        e.fieldErrors.forEach {
            log.info("fieldError.code : {}", it.code)
            log.info("fieldError.field : {}", it.field)
            it.arguments?.forEach { argument ->
                log.info("fieldError.arguments : {}", argument)
            }
        }
        e.detailMessageArguments.forEach {
            log.info("detailMessageArgument : {}", it)
        }
        e.allErrors.forEach {
            log.info("{}", it.code)
            log.info("{}", it.codes)
            log.info("{}", it.objectName)
            log.info("{}", it.defaultMessage)
            log.info("{}", it.arguments)
        }
        log.info("bindingResult : {}", e.bindingResult)
        log.info("problemDetail : {}", e.body)
        val bindingResult = e.bindingResult
        val fieldError = bindingResult.fieldError

        return ResponseEntity
            .badRequest()
            .body(
                ErrorResponse(
                    ErrorDetail("VALIDATION", ""),
                    MetaResponse(e.fieldError?.defaultMessage?.toInt() ?: ErrorCode.INVALID_VALUE.code)
                )
            )
    }
}