package com.banking.bankingsystem.controller

import com.banking.bankingsystem.model.UserEntity
import com.banking.bankingsystem.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/v1")
class UserController(private val userRepository: UserRepository) {

    @PostMapping("/register")
    fun register(@RequestBody user: UserEntity): ResponseEntity<String> {
        if (userRepository.findByUsername(user.username) != null) {
            return ResponseEntity.badRequest().body(" Username already exists")
        }

        userRepository.save(user)
        return ResponseEntity.ok(" User registered")
    }
}
