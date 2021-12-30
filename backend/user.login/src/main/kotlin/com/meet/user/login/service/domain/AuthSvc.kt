package com.meet.user.login.service.domain

import com.meet.user.login.contract.domain.AuthCt
import com.meet.user.login.dto.UserLoginDTO
import com.meet.user.login.security.AppRole
import com.meet.user.login.security.AppUser
import com.meet.user.login.security.JwtProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.HashMap

@Service("meet_auth")
class AuthSvc : AuthCt{

    @Autowired
    lateinit var jwtProvider: JwtProvider

    override fun login(dto: UserLoginDTO): ResponseEntity<Any> {

        val map = HashMap<String, String?>()

        map["data"] = dto.emailOrUsername


        val res = ResponseEntity<Any>(map, HttpStatus.OK);

        val userDetails:AppUser? = loadUserByUsername(dto.emailOrUsername) as AppUser

        userDetails?: kotlin.run { throw BadCredentialsException("Login credentials seems to be invalid") }

        val detailsMap: MutableMap<String, String?> = mutableMapOf<String, String?>()
        detailsMap["user_token"] = userDetails.userToken

        val token:String? = jwtProvider.createToken(userDetails, detailsMap)
        map["token"] = token


        return res
    }

    override fun loadUserByUsername(username: String): UserDetails? {

        val user = AppUser()
        val permission: Set<GrantedAuthority> = HashSet()

        user.gAuth = permission.plus( GrantedAuthority { "ROLE_" + "USER" })
        user.uname = username
        user.userToken = ""
        user.userType = mutableSetOf(AppRole.CLIENT)
        return user
    }

}