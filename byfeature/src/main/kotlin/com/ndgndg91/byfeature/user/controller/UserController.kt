package com.ndgndg91.byfeature.user.controller

import com.ndgndg91.byfeature.global.protocol.SuccessResponse
import com.ndgndg91.byfeature.global.protocol.toSuccessResponse
import com.ndgndg91.byfeature.user.controller.protocol.request.SignUpRequest
import com.ndgndg91.byfeature.user.controller.protocol.response.SignUpResponse
import com.ndgndg91.byfeature.user.controller.protocol.response.UserResponse
import com.ndgndg91.byfeature.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/api/users/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<SuccessResponse<SignUpResponse>> {
        request.validate()
        val result = userService.signUp(request.email, request.password)
        return ResponseEntity.ok(SignUpResponse(result).toSuccessResponse())
    }

    @GetMapping("/api/users")
    fun find(@RequestParam email: String): ResponseEntity<SuccessResponse<UserResponse>> {
        return ResponseEntity.ok(UserResponse(userService.findByEmail(email)).toSuccessResponse())
    }
}