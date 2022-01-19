package com.meet.user.event.controller.api

import com.meet.user.event.contract.domain.EventCt
import com.meet.user.event.request.CreateEventReq
import com.meet.user.event.request.EditEventReq
import feign.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.HashMap
import javax.validation.Valid

@RestController
@RequestMapping("/event/")
class EventCtrl {

    @Autowired
    lateinit var eventSvc: EventCt

    @GetMapping("status")
    fun status() : ResponseEntity<Any> {

        val map = HashMap<String, String?>()
        map.put("data", "Active")

        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        return res
    }

    @PostMapping("create")
    fun create(@Valid @RequestBody req: CreateEventReq) : ResponseEntity<Any> {
        val map = HashMap<String, Any>()

        return ResponseEntity(map, HttpStatus.OK)
    }

    @PutMapping("edit")
    fun edit(@Valid @RequestBody req: EditEventReq) : ResponseEntity<Any> {
        val map = HashMap<String, Any>()

        return ResponseEntity(map, HttpStatus.OK)
    }

}