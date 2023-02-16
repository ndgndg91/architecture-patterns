package com.ndgndg91.byfeature.global.protocol

import com.ndgndg91.byfeature.global.exception.ErrorCode

data class SuccessResponse<T>(
    val body: T,
    val meta: MetaResponse
)

fun <T> T.toSuccessResponse() = SuccessResponse(this, MetaResponse(ErrorCode.SUCCESS.code))