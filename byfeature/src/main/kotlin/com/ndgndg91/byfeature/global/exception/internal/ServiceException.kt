package com.ndgndg91.byfeature.global.exception.internal

import com.ndgndg91.byfeature.global.exception.RootException

open class ServiceException(statusCode: Int): RootException(statusCode)