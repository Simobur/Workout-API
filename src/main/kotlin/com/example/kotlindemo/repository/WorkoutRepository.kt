package com.example.kotlindemo.repository

import com.example.kotlindemo.model.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface WorkoutRepository : JpaRepository<Workout, Long>