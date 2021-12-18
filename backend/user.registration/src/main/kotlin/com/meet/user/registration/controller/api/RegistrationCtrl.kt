package com.meet.user.registration.controller.api

import com.meet.user.registration.request.core.RegistrationReq
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/join/")
class RegistrationCtrl {

    @PostMapping("m")
    fun join(@RequestBody registrationReq: RegistrationReq) : ResponseEntity<Any> {

        val map = HashMap<String, Objects?>()
        map.put("data", null)

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }
}