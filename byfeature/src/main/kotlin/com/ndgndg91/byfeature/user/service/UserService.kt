package com.ndgndg91.byfeature.user.service

import com.ndgndg91.byfeature.domain.User
import com.ndgndg91.byfeature.global.exception.internal.ServiceException
import com.ndgndg91.byfeature.global.exception.internal.user.UserNotFoundException
import com.ndgndg91.byfeature.user.adaptor.UserRepository
import com.ndgndg91.byfeature.user.service.dto.event.SignUpEvent
import com.ndgndg91.byfeature.user.service.dto.result.SignUpResult
import com.ndgndg91.byfeature.user.service.dto.result.UserPagingResult
import com.ndgndg91.byfeature.user.service.dto.result.UserResult
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val passwordEncoder: UserPasswordEncoder,
    private val userRepository: UserRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @Transactional(rollbackFor = [Exception::class])
    fun signUp(email: String, password: String): SignUpResult {
        if (userRepository.findByEmail(email) != null) {
            throw ServiceException(HttpStatus.BAD_REQUEST.value())
        }

        val encodedPassword = passwordEncoder.encode(password)
        val user = User.signUp(email, encodedPassword)
        userRepository.save(user)
        applicationEventPublisher.publishEvent(SignUpEvent(this, email))
        return SignUpResult("")
    }

    @Transactional(readOnly = true)
    fun findAllOnPage(firstName: String?, lastName: String?, pageRequest: Pageable): UserPagingResult {
        return userRepository.findAllOnPage(firstName, lastName, pageRequest).let {
            UserPagingResult(
                page = it.number,
                pageSize = it.size,
                totalPages = it.totalPages,
                totalElements = it.totalElements,
                offset = it.pageable.offset,
                content = it.content.map{ u -> UserResult(u) })
        }
    }

    @Transactional(readOnly = true)
    fun findAllOnMore(firstName: String?, lastName: String?, pageRequest: Pageable): UserPagingResult {
        return userRepository.findAllOnMore(firstName, lastName, pageRequest).let {
            UserPagingResult(
                page = it.number,
                pageSize = it.size,
                offset = it.pageable.offset,
                content = it.content.map { u -> UserResult(u) }
            )
        }
    }

    @Transactional(readOnly = true)
    fun findByEmail(email: String): UserResult {
        val user = userRepository.findByEmail(email)?: throw UserNotFoundException(HttpStatus.NOT_FOUND.value())
        return UserResult(user)
    }
}