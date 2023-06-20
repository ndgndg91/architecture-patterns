package com.ndgndg91.byfeature.user.controller.protocol.request

import com.ndgndg91.byfeature.user.controller.protocol.validation.annotation.ValidEmail
import com.ndgndg91.byfeature.user.controller.protocol.validation.annotation.ValidPassword
import com.ndgndg91.byfeature.user.service.dto.command.SignUpCommand


data class SignUpRequest(
    @field:ValidEmail val email: String?,
    @field:ValidPassword val password: String?
) {
    fun toCommand(): SignUpCommand {
        return SignUpCommand(email!!, password!!)
    }
}