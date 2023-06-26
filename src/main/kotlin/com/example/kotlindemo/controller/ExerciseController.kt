package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Exercise
import com.example.kotlindemo.repository.ExerciseRepository
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/workout")
class ExerciseController(private val exerciseRepository: ExerciseRepository) {

    @GetMapping("/exercises")
    fun getAllExercises(): List<Exercise> = exerciseRepository.findAll()

    @GetMapping("/exercises/{id}")
    fun getExercisesByRoutine(@PathVariable(value="id") routine: String): List<Exercise>{
        return exerciseRepository.findByRoutine(routine)
    }

    @PostMapping("/exercises")
    fun createNewExercise(@Valid @RequestBody exercise: Exercise): Exercise =
        exerciseRepository.save(exercise)

    @GetMapping("/exercises/test")
    @ResponseBody
    fun getTest(@RequestParam routine: String): List<Exercise> =
        exerciseRepository.findByRoutine(routine)

}