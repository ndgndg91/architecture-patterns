package com.ndgndg91.byfeature.user.service.dto.command

data class SignUpCommand(
    val email: String,
    val rawPassword: String
)