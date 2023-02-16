package com.ndgndg91.byfeature.domain.repository

import com.ndgndg91.byfeature.domain.User
import org.springframework.data.repository.Repository

interface UserWriteRepository: Repository<User, Long> {

    fun save(entity: User): User
}