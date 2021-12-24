package com.meet.user.registration.entity

import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,

    @Column(name = "u_token", unique = true)
    var userToken:String,

    @Column(name = "name", nullable = false)
    var name:String,

    @Email
    @Column(name = "email", nullable = false, unique = true)
    var email:String,

    @Column(name = "pwd", nullable = false)
    var encodedPass:String,

    @Column(name = "age")
    var age:Short,

    @Column(name = "username", unique = true)
    var username:String,

    @Column(name = "addr")
    var address:String,

    @Column(name = "phone")
    var phone:String

)
