package com.ndgndg91.byfeature.user.controller.protocol.validation.validator

import com.ndgndg91.byfeature.global.exception.ErrorCode
import com.ndgndg91.byfeature.user.controller.protocol.validation.annotation.ValidEmail
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Pattern

class EmailValidator: ConstraintValidator<ValidEmail, String?> {
    private lateinit var emailPattern: Pattern
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        return when {
            value == null -> addConstraint(context, ErrorCode.EMAIL_REQUIRED.code)
            value.length < 10 -> addConstraint(context, ErrorCode.TOO_SHORT_EMAIL.code)
            value.length > 100 -> addConstraint(context, ErrorCode.TOO_LONG_EMAIL.code)
            !emailPattern.matcher(value).find() -> addConstraint(context, ErrorCode.INVALID_EMAIL_PATTERN.code)
            else -> true
        }
    }
    override fun initialize(constraintAnnotation: ValidEmail) {
        // annotation 에 선언된 값들을 사용할 수 있다.
        emailPattern = Pattern.compile(constraintAnnotation.emailPattern)
    }

    private fun addConstraint(context: ConstraintValidatorContext, errorCode: Int): Boolean {
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(errorCode.toString())
            .addConstraintViolation()
        return false
    }
}