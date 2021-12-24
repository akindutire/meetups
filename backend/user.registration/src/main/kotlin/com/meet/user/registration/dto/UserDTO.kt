package com.meet.user.registration.dto

data class UserDTO(
    var name:String,
    var email:String,
    var encodedPass:String,
    var age:Short,
    var username:String,
    var address:String,
    var userToken:String
)
