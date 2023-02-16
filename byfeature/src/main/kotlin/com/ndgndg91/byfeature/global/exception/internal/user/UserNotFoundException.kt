package com.ndgndg91.byfeature.global.exception.internal.user

import com.ndgndg91.byfeature.global.exception.internal.ServiceException

class UserNotFoundException(statusCode: Int) : ServiceException(statusCode)
