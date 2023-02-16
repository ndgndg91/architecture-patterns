package com.ndgndg91.byfeature.user.adaptor

import com.ndgndg91.byfeature.domain.User
import com.ndgndg91.byfeature.domain.repository.UserReadRepository
import com.ndgndg91.byfeature.domain.repository.UserWriteRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val readRepository: UserReadRepository,
    private val writeRepository: UserWriteRepository
) {
    fun findByEmail(email: String): User? {
        return readRepository.findByEmail(email)
    }

    fun save(user: User): User {
        return writeRepository.save(user)
    }
}