package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Workout
import com.example.kotlindemo.repository.WorkoutRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class WorkoutController(private val workoutRepository: WorkoutRepository) {

    @GetMapping("/workouts")
    fun getAllArticles(): List<Workout> =
            workoutRepository.findAll()

    @GetMapping("/workouts/{id}")
    fun getWorkoutById(@PathVariable(value = "id") workoutId: Long): ResponseEntity<Workout>{
        return workoutRepository.findById(workoutId).map{workout -> ResponseEntity.ok(workout)}.orElse(ResponseEntity.notFound().build())
            } 


    @PostMapping("/workouts")
    fun createNewArticle(@Valid @RequestBody workout: Workout): Workout =
            workoutRepository.save(workout)

    @PutMapping("/workouts/{id}")
    fun getWorkoutById (@PathVariable(value="id") workoutId: Long, @Valid @RequestBody newWorkout: Workout):ResponseEntity<Workout>{
        return workoutRepository.findById(workoutId).map{existingWorkout -> val updatedWorkout: Workout = existingWorkout.copy(title=newWorkout.title, content = newWorkout.content)
                ResponseEntity.ok().body(workoutRepository.save(updatedWorkout))}.orElse(ResponseEntity.notFound().build())
                }
        
}