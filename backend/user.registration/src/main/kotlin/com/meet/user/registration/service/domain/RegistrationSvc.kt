package com.meet.user.registration.service.domain

import com.meet.user.registration.contract.domain.RegistrationCt
import com.meet.user.registration.dao.UserDao
import com.meet.user.registration.dto.UserDTO
import com.meet.user.registration.entity.User
import com.meet.user.registration.request.core.RegReq
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.inject.Qualifier

@Service("meet_registration_svc")
class RegistrationSvc: RegistrationCt {

    @Autowired
    lateinit var userDao: UserDao

    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun createUser(req: RegReq) : UserDTO{
        try{
            val userEntity = User()

            userEntity.userToken = UUID.randomUUID().toString()
            userEntity.username = req.username
            userEntity.name = req.name
            userEntity.email = req.email
            userEntity.encodedPass = bCryptPasswordEncoder.encode(req.password)

            val userDto:UserDTO = UserDTO()
            BeanUtils.copyProperties(userEntity, userDto)
            userDao.save(userEntity)
            return userDto
        } catch ( e:Exception ) {
            throw IllegalStateException(e.message)
        }
    }
}