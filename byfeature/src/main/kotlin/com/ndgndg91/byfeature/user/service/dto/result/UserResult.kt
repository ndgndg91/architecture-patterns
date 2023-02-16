package com.ndgndg91.byfeature.user.service.dto.result

import com.ndgndg91.byfeature.domain.User

data class UserResult(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String
) {
    constructor(user: User) : this(
        id = user.id!!,
        email = user.email,
        firstName = user.firstName,
        lastName = user.lastName
    )
}