package com.ndgndg91.byfeature.user.controller.protocol.validation.annotation

import com.ndgndg91.byfeature.user.controller.protocol.validation.validator.PasswordValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordValidator::class])
annotation class ValidPassword(
    val message: String = "Invalid password",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val notAllowed: Array<String> = ["(",")","[","]"]
)
