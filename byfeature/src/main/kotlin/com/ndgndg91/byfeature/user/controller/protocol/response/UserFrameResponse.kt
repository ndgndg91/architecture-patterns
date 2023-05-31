package com.ndgndg91.byfeature.user.controller.protocol.response

import com.ndgndg91.byfeature.global.protocol.Frame

data class UserFrameResponse(
    val frame: Frame,
    val users: List<UserResponse>
)