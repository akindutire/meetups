package com.meet.user.event.dto

import com.meet.user.event.entity.EventType
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

class EventDto {

    lateinit var ev_token:String

    lateinit var type: EventType

    var period: Int = 0

    lateinit var createdAt: LocalDateTime

    lateinit var updatedAt: LocalDateTime

    lateinit var timezone: String
}