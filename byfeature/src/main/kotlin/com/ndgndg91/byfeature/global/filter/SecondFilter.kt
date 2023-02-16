package com.ndgndg91.byfeature.global.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.LoggerFactory

class SecondFilter: Filter {
    private val log = LoggerFactory.getLogger(SecondFilter::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        log.info("filter second")
        chain.doFilter(request, response)
    }
}