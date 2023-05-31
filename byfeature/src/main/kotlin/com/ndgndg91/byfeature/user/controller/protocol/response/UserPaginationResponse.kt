package com.ndgndg91.byfeature.user.controller.protocol.response

import com.ndgndg91.byfeature.global.protocol.Pagination

data class UserPaginationResponse(
    val pagination: Pagination,
    val users: List<UserResponse>
)