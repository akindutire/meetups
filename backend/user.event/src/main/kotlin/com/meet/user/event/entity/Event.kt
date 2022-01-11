package com.meet.user.event.entity

import java.time.LocalDateTime
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
    @Enumerated(EnumType.STRING)
    lateinit var type:EventType

    @Column(name = "period", nullable = false)
    var period: Int = 0

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
}

enum class EventType{
    SCHEDULED,
    SNAPPED,
}