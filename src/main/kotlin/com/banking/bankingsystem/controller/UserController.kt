package com.banking.bankingsystem.controller

import com.banking.bankingsystem.model.Role
import com.banking.bankingsystem.model.User
import com.banking.bankingsystem.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String
)

@RestController
@RequestMapping("/api/auth")
class UserController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<String> {
        if (userRepository.existsByUsername(request.username)) {
            return ResponseEntity.badRequest().body("Username already exists")
        }

        if (userRepository.existsByEmail(request.email)) {
            return ResponseEntity.badRequest().body("Email already exists")
        }

        val user = User(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            email = request.email,
            firstName = request.firstName,
            lastName = request.lastName,
            role = Role.USER
        )

        userRepository.save(user)
        return ResponseEntity.ok("User registered successfully")
    }
}
