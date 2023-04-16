package com.meet.user.event.dao

import com.meet.user.event.entity.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepo:JpaRepository<Event, Long> {

    fun findByEvToken(token:String): Event

}