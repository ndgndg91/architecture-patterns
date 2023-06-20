package com.ndgndg91.byfeature.global.protocol

import com.ndgndg91.byfeature.global.exception.ErrorCode
import com.ndgndg91.byfeature.global.exception.internal.ServiceException

data class ErrorResponse (
    val body: ErrorDetail,
    val meta: MetaResponse
) {
    constructor(e: ServiceException) : this(
        body = ErrorDetail(
            errorType = "SERVICE",
            message = e.message?: ""
        ),
        meta = MetaResponse(ErrorCode.SERVICE.code)
    )
}