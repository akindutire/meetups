package com.meet.user.login.service.domain

import com.meet.user.login.contract.domain.AuthCt
import com.meet.user.login.dto.UserLoginDTO
import com.meet.user.login.security.AppUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthSvc : AuthCt{
    override fun login(dto: UserLoginDTO): Boolean {

        var user:AppUser = loadUserByUsername(dto.emailOrUsername) as AppUser

        return true
    }

    override fun loadUserByUsername(username: String): UserDetails {

        val user = AppUser()
        val permission: Set<GrantedAuthority> = HashSet()

        user.gAuth = permission.plus( GrantedAuthority { "ROLE_" + "USER" })
        user.uname = username

        return user
    }

}