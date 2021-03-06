package com.meet.user.login

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableFeignClients
class UserLoginMsApplication

fun main(args: Array<String>) {
	runApplication<UserLoginMsApplication>(*args)
}
