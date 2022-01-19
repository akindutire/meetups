package com.meet.user.event.request

import com.meet.user.event.entity.EventType
import javax.validation.constraints.NotBlank

class CreateEventReq {

    @NotBlank(message = "Event is required")
    lateinit var name: String

    @NotBlank(message = "Event type is required")
    lateinit var type: EventType

    var expectedCapacity: Short? = null

    var expectedPeriod: Short? = null

}