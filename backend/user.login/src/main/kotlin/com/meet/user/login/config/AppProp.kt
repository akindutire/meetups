package com.meet.user.login.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProp {

    @Value("\${jwt.token.secret}")
    lateinit var JWT_TOKEN_SECRET: String;

    @Value("\${jwt.period.expiration}")
    lateinit var EXPIRATION_SECONDS: String

}