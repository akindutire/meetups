package com.meet.user.registration.controller.api

import com.meet.user.registration.contract.domain.RegistrationCt
import com.meet.user.registration.request.core.RegReq
import com.meet.user.registration.service.domain.RegistrationSvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/reg/")
class RegistrationCtrl {

    @Autowired
    @Qualifier("meet_registration_svc")
    lateinit var registrationSvc: RegistrationCt

    @GetMapping("status")
    fun welcome() : ResponseEntity<Any> {

        val map = HashMap<String, String?>()
        map.put("data", "Active")

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }

    @PostMapping("join")
    fun join(@Valid @RequestBody registrationReq: RegReq) : ResponseEntity<Any> {

        val map = HashMap<String, Objects?>()

        registrationSvc.createUser(registrationReq)

        map.put("data", null)


        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }
}