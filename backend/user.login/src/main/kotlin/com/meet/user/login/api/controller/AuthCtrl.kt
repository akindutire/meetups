package com.meet.user.login.api.controller

import com.meet.user.login.request.UserLoginReq
import com.meet.user.login.service.domain.AuthSvc
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.HashMap
import javax.validation.Valid

@RestController
@RequestMapping("/auth/")
class AuthCtrl {

    lateinit var authSvc: AuthSvc

    @GetMapping("status")
    fun status() : ResponseEntity<Any> {
        println("I got here")
        val map = HashMap<String, String?>()
        map.put("data", "Active")

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }

    @PostMapping("login")
    fun signIn(@Valid @RequestBody req: UserLoginReq) : ResponseEntity<Any> {
        val map = HashMap<String, String?>()

        map.put("data", req.emailOrUsername)

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }
}