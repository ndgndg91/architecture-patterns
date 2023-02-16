package com.ndgndg91.byfeature.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "user_account")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    private var password: String,

    @Column(name = "first_name", nullable = false)
    var firstName: String,
    @Column(name = "last_name", nullable = false)
    var lastName: String,
) {
    companion object {
        fun signUp(email: String, encodedPassword: String): User {
            return User(null, email, encodedPassword, "", "")
        }
    }
}
