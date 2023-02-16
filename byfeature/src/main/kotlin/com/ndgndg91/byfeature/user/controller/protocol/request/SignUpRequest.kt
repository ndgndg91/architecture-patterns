package com.ndgndg91.byfeature.user.controller.protocol.request

import com.ndgndg91.byfeature.global.exception.internal.ValidationException


data class SignUpRequest(
    val email: String,
    val password: String
) {
    fun validate() {
        require(email.length < 100) { throw ValidationException("email exceed 100.") }
        require(password.length < 100) { throw ValidationException("password exceed 100.") }
    }
}