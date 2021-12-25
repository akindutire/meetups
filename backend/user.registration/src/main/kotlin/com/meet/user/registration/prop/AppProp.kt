package com.meet.user.registration.prop

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AppProp {

    @Value("gateway.ip")
    lateinit var GATEWAY_IP:String

}