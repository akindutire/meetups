package com.meet.user.event.request

import com.meet.user.event.entity.EventType
import javax.validation.constraints.NotBlank

class EditEventReq {
    @NotBlank(message = "Event ID is required")
    lateinit var evToken: String

    var expectedCapacity: Short? = null

    var expectedPeriod: Short? = null

    var description: String? = null

}