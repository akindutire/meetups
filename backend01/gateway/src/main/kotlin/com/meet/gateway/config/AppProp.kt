package com.meet.gateway.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProp {
    @Value("\${jwt.token.secret}")
    lateinit var JWT_TOKEN_SECRET: String;

}