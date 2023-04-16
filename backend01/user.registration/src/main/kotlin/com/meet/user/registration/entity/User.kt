package com.meet.user.registration.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(name = "u_token", unique = true)
    lateinit var userToken:String

    @Column(name = "name", nullable = false)
    lateinit var name:String

    @Email
    @Column(name = "email", nullable = false, unique = true)
    lateinit var email:String

    @Column(name = "pwd", nullable = false)
    lateinit var encodedPass:String

    @Column(name = "age")
    var age:Short = 0

    @Column(name = "username", unique = true)
    lateinit var username:String

    @Column(name = "addr")
    var address:String? = null

    @Column(name = "phone")
    var phone:String? = null

    @Column(name="created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name="updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()


    @Column(name = "timezone")
    var timezone: String = TimeZone.getDefault().displayName

}
