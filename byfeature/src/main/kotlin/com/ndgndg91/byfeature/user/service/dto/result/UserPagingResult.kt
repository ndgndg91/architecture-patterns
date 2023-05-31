package com.ndgndg91.byfeature.user.service.dto.result

data class UserPagingResult(
    val page: Int,
    val pageSize: Int,
    val totalPages: Int,
    val totalElements: Long,
    val offset: Long,
    val content: List<UserResult>
)