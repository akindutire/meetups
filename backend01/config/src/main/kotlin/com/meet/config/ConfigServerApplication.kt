package com.meet.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.bus.BusBridge
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableConfigServer
class ConfigServerApplication

fun main(args: Array<String>) {
	runApplication<ConfigServerApplication>(*args)
}
