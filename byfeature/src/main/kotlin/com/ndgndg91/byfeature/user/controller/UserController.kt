package com.ndgndg91.byfeature.user.controller

import com.ndgndg91.byfeature.global.protocol.Frame
import com.ndgndg91.byfeature.global.protocol.Pagination
import com.ndgndg91.byfeature.global.protocol.SuccessResponse
import com.ndgndg91.byfeature.global.protocol.request.OffsetBasedPageRequest
import com.ndgndg91.byfeature.global.protocol.toSuccessResponse
import com.ndgndg91.byfeature.user.controller.protocol.request.SignUpRequest
import com.ndgndg91.byfeature.user.controller.protocol.response.*
import com.ndgndg91.byfeature.user.service.UserService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/api/users/sign-up")
    fun signUp(@RequestBody @Valid request: SignUpRequest): ResponseEntity<SuccessResponse<SignUpResponse>> {
        val result = userService.signUp(request.toCommand())
        return ResponseEntity.ok(SignUpResponse(result).toSuccessResponse())
    }

    @GetMapping("/api/users/{id}")
    fun queryAndPath(
        @RequestParam @Min(0) size: Int,
        @PathVariable @Min(0) id: Long
    ): ResponseEntity<SuccessResponse<Unit>> {
        return ResponseEntity.ok(Unit.toSuccessResponse())
    }

    @GetMapping("/api/users")
    fun find(
        @RequestParam(required = false) firstName: String?,
        @RequestParam(required = false) lastName: String?,
        @RequestParam(required = false, defaultValue = "page") type: String,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
        @RequestParam(required = false) offset: Long?
    ): ResponseEntity<SuccessResponse<*>> {
        val pageRequest = if (offset == null) {
            PageRequest.of(page, size)
        } else {
            OffsetBasedPageRequest(offset, size)
        }

        return if (type == "page") {
            userService.findAllOnPage(firstName, lastName, pageRequest).let {
                UserPaginationResponse(
                    Pagination(it.page, it.pageSize, it.totalPages, it.totalElements, it.offset),
                    it.content.map { ur -> UserResponse(ur) }
                )
            }.toSuccessResponse().let { ResponseEntity.ok(it) }
        } else {
            userService.findAllOnMore(firstName, lastName, pageRequest).let {
                UserFrameResponse(
                    Frame(it.offset),
                    it.content.map { ur -> UserResponse(ur) }
                )
            }.toSuccessResponse().let { ResponseEntity.ok(it) }
        }
    }
}