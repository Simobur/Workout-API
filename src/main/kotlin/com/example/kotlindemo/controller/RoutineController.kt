package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Routine
import com.example.kotlindemo.repository.RoutineRepository
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid



@RestController
@RequestMapping("/api/workout")
class RoutineController(private val routineRepository: RoutineRepository) {

    @GetMapping("/routines")
    fun getAllRoutines(): List<Routine> = routineRepository.findAll()
    @GetMapping("/routines/{id}")
    fun getRoutinesByUserId(@PathVariable(value="id") routineId: Long): ResponseEntity<Routine>{
        return routineRepository.findById(routineId).map{r -> ResponseEntity.ok(r)}.orElse(ResponseEntity.notFound().build())
    }
    @PostMapping("/routines")
    fun addRoutine(@Valid @RequestBody routine: Routine): Routine = routineRepository.save(routine)

    @PutMapping("/routines/{id}")
    fun updateRoutineById(@PathVariable(value="id") routineId: Long, @Valid @RequestBody newRoutine: Routine): ResponseEntity<Routine>{
        return routineRepository.findById(routineId).map{existingRoutine ->
            val updatedRoutine: Routine = existingRoutine.copy(name=newRoutine.name)

            ResponseEntity.ok().body(routineRepository.save(updatedRoutine))
        }.orElse(ResponseEntity.notFound().build())
    }
    @DeleteMapping("/routines/{id}")
    fun deleteRoutine(@PathVariable(value = "id") routineId: Long): ResponseEntity<Void>{

        return routineRepository.findById(routineId).map{r -> routineRepository.delete(r)
        ResponseEntity<Void>(HttpStatus.OK)}.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/routines/user")
    fun getRoutinesByUserId(@RequestParam id: String): List<Routine>{
        return routineRepository.findByUserid(id)
    }


}