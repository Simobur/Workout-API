package com.example.kotlindemo.repository

import com.example.kotlindemo.model.Routine
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface RoutineRepository: JpaRepository<Routine, Long> {
    fun findByName (name: String): List<Routine>

    fun findByUserid(userid: String): List<Routine>
}

