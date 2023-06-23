package com.example.kotlindemo.controller

import com.example.kotlindemo.model.User
import com.example.kotlindemo.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
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
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> = userRepository.findAll()

    @PostMapping("/users")
    fun createNewUser(@Valid @RequestBody user: User): User = userRepository.save(user)

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable(value="id") userId: Long): ResponseEntity<User>{
        return userRepository.findById(userId).map {user -> ResponseEntity.ok(user)}.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/users/{id}")
    fun updateUserById(@PathVariable(value= "id") userId: Long, @Valid @RequestBody newUser: User): ResponseEntity<User>{

        return userRepository.findById(userId).map {existingUser ->
            val updatedUser: User = existingUser.copy(name = newUser.name)

            ResponseEntity.ok().body(userRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable(value = "id") userId: Long): ResponseEntity<Void> {

    return userRepository.findById(userId).map {user -> userRepository.delete(user)
    ResponseEntity<Void>(HttpStatus.OK)}.orElse(ResponseEntity.notFound().build())
    }
}