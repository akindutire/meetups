package com.meet.user.password.reset

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
class PasswordResetApplication

fun main(args: Array<String>) {
	runApplication<PasswordResetApplication>(*args)
}
