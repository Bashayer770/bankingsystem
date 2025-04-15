package com.banking.bankingsystem.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "kyc")
data class KycEntity(
    @Id
    val userId: Long,

    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate
)
