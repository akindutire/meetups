package com.meet.user.event.service.client

import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import java.time.ZoneId

@FeignClient(name = "USER-PROFILE", fallbackFactory = UserMSClientFallbackFactory::class)
interface UserMsClient {

    fun getUserTimezone() : String {
//        @Todo create endpoint on user ms and return a reliable dto
        return ""
    }
}

@Component
class UserMSClientFallbackFactory:FallbackFactory<UserMsClient> {
    override fun create(cause: Throwable?): UserMsClient {
        return UserMSClientFallback(cause)
    }

}

class UserMSClientFallback(private var cause: Throwable?) :UserMsClient {


    override fun getUserTimezone(): String {
        return ZoneId.systemDefault().toString()
    }
}