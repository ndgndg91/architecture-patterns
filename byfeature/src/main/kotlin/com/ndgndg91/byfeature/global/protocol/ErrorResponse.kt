package com.ndgndg91.byfeature.global.protocol

import com.ndgndg91.byfeature.global.exception.ErrorCode
import com.ndgndg91.byfeature.global.exception.internal.ServiceException
import com.ndgndg91.byfeature.global.exception.internal.ValidationException

data class ErrorResponse (
    val body: ErrorDetail,
    val meta: MetaResponse
) {
    constructor(e: ValidationException) : this(
        body = ErrorDetail(
            errorType = "VALIDATION",
            message = e.message
        ),
        meta = MetaResponse(ErrorCode.INVALID_VALUE.code)
    )

    constructor(e: ServiceException) : this(
        body = ErrorDetail(
            errorType = "SERVICE",
            message = e.message?: ""
        ),
        meta = MetaResponse(ErrorCode.SERVICE.code)
    )
}