package com.ndgndg91.byfeature.global.exception

import org.springframework.http.HttpStatus

open class RootException(val statusCode: Int = HttpStatus.OK.value()): RuntimeException()