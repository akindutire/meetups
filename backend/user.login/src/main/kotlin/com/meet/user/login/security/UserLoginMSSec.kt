package com.meet.user.login.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class UserLoginMSSec: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().cors()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
//            .hasIpAddress(prop.GATEWAY_IP)
            .antMatchers(HttpMethod.OPTIONS, "**/**").permitAll()
            .antMatchers(HttpMethod.HEAD, "**/**").permitAll()
            .anyRequest().authenticated()

    }
}