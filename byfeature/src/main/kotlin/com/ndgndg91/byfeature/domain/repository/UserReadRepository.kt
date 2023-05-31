package com.ndgndg91.byfeature.domain.repository

import com.ndgndg91.byfeature.domain.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UserReadRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String): User?

    fun findAllByFirstName(firstName: String, pageable: Pageable): Page<User>

    fun findAllByLastName(lastName: String, pageable: Pageable): Page<User>

    fun findAllByFirstNameAndLastName(firstName: String, lastName: String, pageable: Pageable): Page<User>
}