package com.meet.user.registration.service.domain

import com.meet.user.registration.contract.domain.RegistrationCt
import com.meet.user.registration.dao.UserDao
import com.meet.user.registration.dto.UserDTO
import com.meet.user.registration.entity.User
import com.meet.user.registration.request.core.RegReq
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.inject.Qualifier

@Service("meet_registration_svc")
class RegistrationSvc: RegistrationCt {

    @Autowired
    lateinit var userDao: UserDao

    override fun createUser(req: RegReq) : UserDTO{

        val userDto = UserDTO(req.name, "", "", 0, "", "", UUID.randomUUID().toString())
        // val userEntity = User(userToken = userDto.userToken)
//
//        BeanUtils.copyProperties(userDto, userEntity)
//        userDao.save()
        return userDto

    }
}