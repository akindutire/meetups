package com.meet.user.login.contract.domain

import com.meet.user.login.dto.UserLoginDTO
import org.springframework.security.core.userdetails.UserDetailsService

interface AuthCt : UserDetailsService{
    fun login(dto: UserLoginDTO): Boolean
}