package com.ndgndg91.byfeature.user.service.dto.result

data class UserPagingResult(
    val page: Int,
    val pageSize: Int,
    val totalPages: Int? = null,
    val totalElements: Long? = null,
    val offset: Long,
    val content: List<UserResult>
)