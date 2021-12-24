package com.meet.user.registration.dao

import com.meet.user.registration.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao :JpaRepository<User, Long> {
}