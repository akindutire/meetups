package com.meet.user.registration.contract.domain

import com.meet.user.registration.dto.UserDTO
import com.meet.user.registration.request.core.RegReq

interface RegistrationCt {
    fun createUser(req: RegReq):UserDTO;
}