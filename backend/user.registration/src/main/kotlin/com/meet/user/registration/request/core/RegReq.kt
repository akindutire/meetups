package com.meet.user.registration.request.core

import lombok.Getter
import lombok.Setter
import javax.validation.ValidationException
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegReq (

    @NotBlank
    var name: String,

    @NotBlank
    var username: String,

    @Email(regexp = "^([A-Za-z]*[A-Za-z0-9_.-]+)@[A-Za-z0-9]+(\\.)([A-Za-z0-9_.]*[A-Za-z]+)$", message = "A valid email is required")
    var email: String,

    @NotBlank
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
