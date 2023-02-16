package com.ndgndg91.byfeature.user.controller.protocol.response

import com.ndgndg91.byfeature.user.service.dto.result.UserResult

data class UserResponse(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String
) {
    constructor(user: UserResult): this(
        id = user.id,
        email = user.email,
        firstName = user.firstName,
        lastName = user.lastName
    )
}
