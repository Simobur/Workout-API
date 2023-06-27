package com.example.kotlindemo.model

import com.example.kotlindemo.repository.ExerciseRepository
import com.example.kotlindemo.repository.RoutineRepository
import org.springframework.stereotype.Service
import com.example.kotlindemo.model.Exercise
import com.example.kotlindemo.model.Routine
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class JoinService(
    private val exerciseRepository: ExerciseRepository,
    private val routineRepository: RoutineRepository
){

    @GetMapping("/test")
    fun performJoin(): List<Pair<Routine, Exercise>>{
        val exerciseList = exerciseRepository.findAll()
        val routineList = routineRepository.findAll()

        return exerciseList.flatMap{ex -> routineList.filter{rou ->"${rou.id}" == "${ex.routine}"}.map{rou->Pair(rou, ex)}}
    }


}