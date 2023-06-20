package com.ndgndg91.byfeature.user.controller.protocol.validation.validator

import com.ndgndg91.byfeature.global.exception.ErrorCode
import com.ndgndg91.byfeature.user.controller.protocol.validation.annotation.ValidPassword
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordValidator: ConstraintValidator<ValidPassword, String?> {
    private lateinit var notAllowed: Set<String>
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        return when {
            value == null -> addConstraint(context, ErrorCode.PASSWORD_REQUIRED.code)
            value.length < 10 -> addConstraint(context, ErrorCode.TOO_SHORT_PASSWORD.code)
            value.length > 50 -> addConstraint(context, ErrorCode.TOO_LONG_PASSWORD.code)
            notAllowed.any { value.contains(it) } -> addConstraint(context, ErrorCode.INCLUDE_INVALID_CHARACTER_PASSWORD.code)
            else -> true
        }
    }
    override fun initialize(constraintAnnotation: ValidPassword) {
        notAllowed = constraintAnnotation.notAllowed.toSet()
    }

    private fun addConstraint(context: ConstraintValidatorContext, errorCode: Int): Boolean {
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(errorCode.toString()).addConstraintViolation()
        return false
    }
}