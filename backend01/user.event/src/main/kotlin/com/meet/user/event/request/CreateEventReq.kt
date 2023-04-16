package com.meet.user.event.request

import com.meet.user.event.entity.EventType
import javax.validation.constraints.NotBlank

class CreateEventReq {

    @NotBlank(message = "Event type is required")
    lateinit var type: EventType

    @NotBlank(message = "Event description is required")
    lateinit var description: String

    var expectedCapacity: Short? = null

    var expectedPeriod: Short? = null

}