package com.meet.user.login.contract.domain

import com.meet.user.login.dto.UserLoginDTO

interface AuthCt {
    fun login(dto: UserLoginDTO): Boolean
}