package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Exercise
import com.example.kotlindemo.repository.ExerciseRepository
import com.example.kotlindemo.repository.RoutineRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/workout")
class ExerciseController(private val exerciseRepository: ExerciseRepository, private val routineRepository: RoutineRepository) {

    @GetMapping("/exercises")
    fun getAllExercises(): List<Exercise> = exerciseRepository.findAll()

    @GetMapping("/exercises/{id}")
    fun getExercisesByRoutine(@PathVariable(value="id") routine: String): List<Exercise>{

        return exerciseRepository.findByRoutine(routine)

            }

    @PostMapping("/exercises")
    fun createNewExercise(@Valid @RequestBody exercise: Exercise): Exercise =
        exerciseRepository.save(exercise)

    @DeleteMapping("/exercises/{id}")
    fun deleteExercise(@PathVariable(value = "id") exerciseId: Long): ResponseEntity<Void>{
        return exerciseRepository.findById(exerciseId).map{e -> exerciseRepository.delete(e)
        ResponseEntity<Void>(HttpStatus.OK)}.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/exercises/routine")
    @ResponseBody
    fun getTest(@RequestParam id: String): List<Exercise> =
        exerciseRepository.findByRoutine(id)

}