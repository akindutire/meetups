package com.meet.user.registration.controller.api

import com.meet.user.registration.request.core.RegReq
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/reg/")
class RegistrationCtrl {

    @GetMapping("status")
    fun welcome() : ResponseEntity<Any> {

        val map = HashMap<String, String?>()
        map.put("data", "Active")

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }

    @PostMapping("join")
    fun join(@RequestBody registrationReq: RegReq) : ResponseEntity<Any> {

        val map = HashMap<String, Objects?>()
        map.put("data", null)

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }
}