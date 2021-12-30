package com.meet.user.login.security

import com.meet.user.login.config.AppProp
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class JwtProvider {

    @Autowired
    lateinit var prop: AppProp

    fun createToken(userDetails: AppUser, details: MutableMap<String, String?>): String? {

        val c = Calendar.getInstance()
        c.add(Calendar.SECOND, prop.EXPIRATION_SECONDS.toInt())
        val detail = details?: HashMap()

        return Jwts.builder()
            .setSubject(userDetails.username)
            .claim("details", detail)
            .claim("detail_user_token", details?.get("user_token"))
            .claim("granted_authorities", userDetails.authorities)
            .setIssuedAt(Date())
            .setExpiration(c.time)
            .signWith(SignatureAlgorithm.HS512, prop.JWT_TOKEN_SECRET)
            .compact()
    }
}