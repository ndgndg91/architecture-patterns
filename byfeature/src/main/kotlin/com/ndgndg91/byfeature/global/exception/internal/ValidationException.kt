package com.ndgndg91.byfeature.global.exception.internal

import com.ndgndg91.byfeature.global.exception.RootException
import org.springframework.http.HttpStatus

class ValidationException(
    override val message: String,
    httpStatusCode: Int = HttpStatus.BAD_REQUEST.value(),
) : RootException(httpStatusCode)