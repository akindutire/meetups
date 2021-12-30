package com.meet.user.registration.security

import com.meet.user.registration.prop.AppProp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class UserRegMsWebSecurity: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var prop: AppProp

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .cors()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/register/**").permitAll()
//            .hasIpAddress(prop.GATEWAY_IP)
            .antMatchers(HttpMethod.OPTIONS, "**/**").permitAll()
            .antMatchers(HttpMethod.HEAD, "**/**").permitAll()
            .anyRequest().authenticated()

            println()
    }
}