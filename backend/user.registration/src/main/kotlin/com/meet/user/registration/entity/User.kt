package com.meet.user.registration.entity

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
    lateinit var address:String

    @Column(name = "phone")
    lateinit var phone:String

}
