package com.meet.user.login.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AppUser : UserDetails{

    lateinit var gAuth:Set<GrantedAuthority>
    lateinit var pawd: String
    lateinit var uname: String
    var isAccountNonExpiredd: Boolean = false
    var isAccountNonLockedd: Boolean = false
    var isCredentialsNonExpiredd: Boolean = false
    var isEnabledd: Boolean = false
    lateinit var userType: Set<AppRole>
    lateinit var userToken: String

    override fun getAuthorities(): Set<GrantedAuthority> = gAuth

    override fun getPassword(): String = pawd

    override fun getUsername(): String = uname

    override fun isAccountNonExpired(): Boolean = isAccountNonExpiredd

    override fun isAccountNonLocked(): Boolean = isAccountNonLockedd

    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpiredd

    override fun isEnabled(): Boolean = isEnabledd

}

