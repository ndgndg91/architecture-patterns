package com.ndgndg91.byfeature.user.controller.protocol.response

import com.ndgndg91.byfeature.user.service.dto.result.SignUpResult

data class SignUpResponse(
    val t: String
) {
    constructor(result: SignUpResult) : this(
        result.t
    )
}