package com.meet.user.password.reset.controller.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/password/reset/")
class PasswordResetCtrl {

    @GetMapping("status")
    fun status() : ResponseEntity<Any> {

        val map = HashMap<String, String?>()
        map.put("data", "Active")

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }

}