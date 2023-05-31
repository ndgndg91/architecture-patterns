package com.ndgndg91.byfeature.global.protocol

data class Pagination(
    val page: Int,
    val pageSize: Int,
    val totalPages: Int,
    val totalElements: Long,
    val offset: Long
)