package com.ndgndg91.byfeature.domain.repository

import com.ndgndg91.byfeature.domain.User
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface UserReadSliceRepository: JpaRepository<User, Long> {

    fun findAllByFirstName(firstName: String, pageable: Pageable): Slice<User>

    fun findAllByLastName(lastName: String, pageable: Pageable): Slice<User>

    fun findAllByFirstNameAndLastName(firstName: String, lastName: String, pageable: Pageable): Slice<User>

}