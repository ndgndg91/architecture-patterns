package com.ndgndg91.byfeature.global.protocol.request

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class OffsetBasedPageRequest(
    private val offset: Long,
    private val limit: Int,
    private val sort: Sort = Sort.unsorted()
): Pageable {

    init {
        if (offset < 0) {
            throw IllegalArgumentException("Offset index must not be less than zero!")
        }

        if (limit < 1) {
            throw IllegalArgumentException("Limit must not be less than one!")
        }
    }

    override fun getPageNumber(): Int {
        return (offset / limit).toInt()
    }

    override fun getPageSize(): Int {
        return limit
    }

    override fun getOffset(): Long {
        return offset
    }

    override fun getSort(): Sort {
        return sort
    }

    override fun next(): Pageable {
        return OffsetBasedPageRequest(getOffset() + pageSize, pageSize, sort)
    }

    private fun previous(): OffsetBasedPageRequest {
        return if (hasPrevious()) OffsetBasedPageRequest(getOffset() - pageSize, pageSize, getSort()) else this
    }

    override fun previousOrFirst(): Pageable {
        return if (hasPrevious()) previous()  else first()
    }

    override fun first(): Pageable {
        return OffsetBasedPageRequest(0, pageSize, getSort())
    }

    override fun withPage(pageNumber: Int): Pageable {
        throw UnsupportedOperationException()
    }

    override fun hasPrevious(): Boolean {
        return offset > limit
    }
}