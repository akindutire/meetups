package com.meet.user.registration.request.core

import org.hibernate.validator.constraints.Length
import javax.validation.ValidationException
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegReq (

    @NotBlank(message = "Name can't be empty")
    var name: String,

    @NotBlank(message = "Username is required")
    var username: String,

    @Email(regexp = "^([A-Za-z]*[A-Za-z0-9_.-]+)@[A-Za-z0-9]+(\\.)([A-Za-z0-9_.]*[A-Za-z]+)$", message = "A valid email is required")
    var email: String,

    @NotBlank(message = "Password is required")
    @Length(min = 6, message = "Password must have a minimum of 6 characters")
    var password: String,

    @NotBlank
    var confirmPassword: String? = null

    ){
    init {

        if (password != confirmPassword) {
            throw ValidationException("Password and confirm password doesn't match")
        }

    }
}
