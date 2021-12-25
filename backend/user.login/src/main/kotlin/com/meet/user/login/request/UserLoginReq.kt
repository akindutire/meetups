package com.meet.user.login.request

import javax.validation.constraints.NotEmpty

class UserLoginReq {

    @NotEmpty(message = "email or username is required")
    lateinit var emailOrUsername:String

    @NotEmpty(message = "password is required")
    lateinit var pwd:String
}