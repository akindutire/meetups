package com.meet.gateway.security

import com.meet.gateway.config.AppProp
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {

    @Autowired
    lateinit var prop: AppProp

    fun getAllClaimsFromToken(token: String): Claims? {
        return Jwts.parser().setSigningKey(prop.JWT_TOKEN_SECRET).parseClaimsJws(token).body
    }

    fun isJWTValid(token: String): Boolean {
        return try{
            getAllClaimsFromToken(token)?.expiration?.before(Date())?:false
        } catch (e: Exception) {
            //log e?.message
            false
        }

    }

}