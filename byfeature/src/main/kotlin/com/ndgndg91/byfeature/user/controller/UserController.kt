package com.ndgndg91.byfeature.user.controller

import com.ndgndg91.byfeature.global.protocol.Pagination
import com.ndgndg91.byfeature.global.protocol.SuccessResponse
import com.ndgndg91.byfeature.global.protocol.toSuccessResponse
import com.ndgndg91.byfeature.user.controller.protocol.request.SignUpRequest
import com.ndgndg91.byfeature.user.controller.protocol.response.SignUpResponse
import com.ndgndg91.byfeature.user.controller.protocol.response.UserPaginationResponse
import com.ndgndg91.byfeature.user.controller.protocol.response.UserResponse
import com.ndgndg91.byfeature.user.service.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/api/users/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<SuccessResponse<SignUpResponse>> {
        request.validate()
        val result = userService.signUp(request.email, request.password)
        return ResponseEntity.ok(SignUpResponse(result).toSuccessResponse())
    }

    @GetMapping("/api/users")
    fun find(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int
    ): ResponseEntity<SuccessResponse<UserPaginationResponse>> {
        val pageRequest = PageRequest.of(page, size)
        return userService.findAll(pageRequest).let {
                UserPaginationResponse(
                    Pagination(it.page, it.pageSize, it.totalPages, it.totalElements, it.offset),
                    it.content.map { ur -> UserResponse(ur) }
                )
            }.toSuccessResponse().let { ResponseEntity.ok(it) }
    }
}