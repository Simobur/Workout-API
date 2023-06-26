package com.example.kotlindemo.repository

import com.example.kotlindemo.model.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ExerciseRepository: JpaRepository<Exercise, Long> {
    fun findByRoutine(routine: String): List<Exercise>
}