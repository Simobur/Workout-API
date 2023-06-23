package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Routine
import com.example.kotlindemo.repository.RoutineRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid



@RestController
@RequestMapping("/api/workout")
class RoutineController(private val routineRepository: RoutineRepository) {

    @GetMapping("/routines")
    fun getAllRoutines(): List<Routine> = routineRepository.findAll()

    @PostMapping("/routines")
    fun addRoutine(@Valid @RequestBody routine: Routine): Routine = routineRepository.save(routine)

    @GetMapping("/routines/name")
    fun getRoutinesByUserId(): List<Routine>{
        return routineRepository.findByName("Chest Routine")
    }
    @GetMapping("/routines/{id}")
    fun getRoutinesByUserName(@PathVariable(value="id") userId: String): List<Routine>{
        return routineRepository.findByUserid(userId)
    }
}