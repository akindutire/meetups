package com.meet.user.login.service.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "USER-REG")
interface RegSvcClient {

    @GetMapping("/users/list")
    fun getRegisteredUser () {

    }
}