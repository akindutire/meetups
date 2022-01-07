package com.meet.user.registration.dto

class UserDTO {

    lateinit var name:String;

    lateinit var email:String;

    lateinit var encodedPass:String;

    var age:Short = 0

    lateinit var username:String
    var address:String? = null
    lateinit var userToken:String
}
