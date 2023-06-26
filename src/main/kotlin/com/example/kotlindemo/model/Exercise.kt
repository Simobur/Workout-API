package com.example.kotlindemo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Exercise(
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotBlank
    val name: String = "",

    @get: NotBlank
    val rep: String = "",

    @get: NotBlank
    val routine: String = ""

    )