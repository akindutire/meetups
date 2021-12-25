package com.meet.user.registration.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.beans
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class AppConfig {

    @Bean
    fun bcrypt():BCryptPasswordEncoder = BCryptPasswordEncoder()

}