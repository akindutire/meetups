package com.meet.user.registration.controller.api

import com.meet.user.registration.contract.domain.RegistrationCt
import com.meet.user.registration.request.core.RegReq
import com.meet.user.registration.service.domain.RegistrationSvc
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/register/")
class RegistrationCtrl {

    @Autowired
    @Qualifier("meet_registration_svc")
    lateinit var registrationSvc: RegistrationCt

    @GetMapping("status")
    fun status() : ResponseEntity<Any> {

        val map = HashMap<String, String?>()
        map.put("data", "Active")

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }

    @PostMapping("step1")
    fun join(@Valid @RequestBody registrationReq: RegReq) : ResponseEntity<Any> {

        val map = HashMap<String, Any>()


        val data = registrationSvc.createUser(registrationReq)
        map["data"] = data


        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }
}
