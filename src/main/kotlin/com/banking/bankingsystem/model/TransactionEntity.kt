package com.banking.bankingsystem.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val sourceAccount: String?,
    val destinationAccount: String?,
    val amount: Double,
    val type: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
