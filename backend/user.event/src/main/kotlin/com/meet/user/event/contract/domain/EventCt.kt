package com.meet.user.event.contract.domain

import com.meet.user.event.dto.EventDto
import com.meet.user.event.request.CreateEventReq
import com.meet.user.event.request.EditEventReq

interface EventCt {
    fun create(req: CreateEventReq) : EventDto
    fun edit(req: EditEventReq) : EventDto
}