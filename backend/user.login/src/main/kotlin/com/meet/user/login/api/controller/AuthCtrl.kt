package com.meet.user.login.api.controller

import com.meet.user.login.contract.domain.AuthCt
import com.meet.user.login.dto.UserLoginDTO
import com.meet.user.login.request.UserLoginReq
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.HashMap
import javax.validation.Valid

@RestController
@RequestMapping("/auth/")
class AuthCtrl {

    @Autowired
    lateinit var authSvc: AuthCt

    @GetMapping("status")
    fun status() : ResponseEntity<Any> {

        val map = HashMap<String, String?>()
        map["data"] = "Active"

        val res = ResponseEntity<Any>(map, HttpStatus.OK)

        return res
    }

    @PostMapping("login")
    fun signIn(@Valid @RequestBody req: UserLoginReq) : ResponseEntity<Any> {
        val dto: UserLoginDTO = UserLoginDTO()
        BeanUtils.copyProperties(req, dto)
        return authSvc.login(dto)
    }
}