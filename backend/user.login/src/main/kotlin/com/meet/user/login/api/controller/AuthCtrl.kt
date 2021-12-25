package com.meet.user.login.api.controller

import com.meet.user.login.request.UserLoginReq
import com.meet.user.login.service.domain.AuthSvc
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth/")
class AuthCtrl {

    lateinit var authSvc: AuthSvc

    @PostMapping("login")
    fun signIn(@Valid @RequestBody req: UserLoginReq) {

    }
}