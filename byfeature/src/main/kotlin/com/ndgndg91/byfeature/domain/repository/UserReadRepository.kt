package com.ndgndg91.byfeature.domain.repository

import com.ndgndg91.byfeature.domain.User
import org.springframework.data.repository.Repository

interface UserReadRepository: Repository<User, Long> {

    fun findByEmail(email: String): User?
}