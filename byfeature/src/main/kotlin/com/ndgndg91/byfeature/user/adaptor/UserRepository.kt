package com.ndgndg91.byfeature.user.adaptor

import com.ndgndg91.byfeature.domain.User
import com.ndgndg91.byfeature.domain.repository.UserReadRepository
import com.ndgndg91.byfeature.domain.repository.UserReadSliceRepository
import com.ndgndg91.byfeature.domain.repository.UserWriteRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Repository


@Repository
class UserRepository(
    private val readRepository: UserReadRepository,
    private val readSliceRepository: UserReadSliceRepository,
    private val writeRepository: UserWriteRepository
) {
    fun findAllOnPage(firstName: String?, lastName: String?, pageRequest: Pageable): Page<User> {
        return when {
            firstName != null && lastName != null -> readRepository.findAllByFirstNameAndLastName(firstName, lastName, pageRequest)
            firstName != null && lastName == null -> readRepository.findAllByFirstName(firstName, pageRequest)
            firstName == null && lastName != null -> readRepository.findAllByLastName(lastName, pageRequest)
            else -> throw RuntimeException()
        }
    }

    fun findAllOnMore(firstName: String?, lastName: String?, pageRequest: Pageable): Slice<User> {
        return when {
            firstName != null && lastName != null -> readSliceRepository.findAllByFirstNameAndLastName(firstName, lastName, pageRequest)
            firstName != null && lastName == null -> readSliceRepository.findAllByFirstName(firstName, pageRequest)
            firstName == null && lastName != null -> readSliceRepository.findAllByLastName(lastName, pageRequest)
            else -> throw RuntimeException()
        }
    }

    fun findByEmail(email: String): User? {
        return readRepository.findByEmail(email)
    }

    fun save(user: User): User {
        return writeRepository.save(user)
    }
}