package com.meet.user.event.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_events")
class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0L

    @Column(name = "ev_token", nullable = false, unique = true)
    lateinit var ev_token:String

    @Column(name = "type", nullable = false)
    lateinit var type:EventType

    @Column(name = "period", nullable = false)
    var period: Int = 0

    @Column(name = "created_at")
    var createdAt: Date =
}

enum class EventType{
    SCHEDULED,
    SNAPPED,

}