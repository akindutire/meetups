package com.meet.user.event.service.domain

import com.meet.user.event.contract.domain.EventCt
import com.meet.user.event.dao.EventRepo
import com.meet.user.event.dto.EventDto
import com.meet.user.event.entity.Event
import com.meet.user.event.request.CreateEventReq
import com.meet.user.event.request.EditEventReq
import feign.FeignException
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Component
class EventSvc:EventCt {

    lateinit var eventRepo: EventRepo

    override fun create(req: CreateEventReq) : EventDto {

        var e = Event()
        e.ev_token = UUID.randomUUID().toString()
        e.period = req.expectedPeriod as Int
        e.type = req.type
        e.capacity = req.expectedCapacity as Int
        e.description = req.description.toString()
        e.createdAt = LocalDateTime.now()
        e.updatedAt = LocalDateTime.now()

        e.timezone = ""

        e = eventRepo.save(e)
        val et = EventDto()

        BeanUtils.copyProperties(e, et)
        return et
    }

    override fun edit(req: EditEventReq) : EventDto {

        val timezone = ""

        val e: Event = eventRepo.findByEvToken(req.evToken)

        e.updatedAt = LocalDateTime.now().atZone(ZoneId.of(timezone)).toLocalDateTime()
        e.period = req.expectedPeriod as Int;
        e.capacity = req.expectedCapacity as Int
        e.description = req.description.toString()

        val et = EventDto()
        BeanUtils.copyProperties(e, et)
        return et
    }
}