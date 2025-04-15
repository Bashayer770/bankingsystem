package com.banking.bankingsystem.model

import jakarta.persistence.*
import org.hibernate.validator.constraints.UUID

@Entity
@Table(name = "accounts")
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val balance: Double,

    @Column(nullable = false)
    val isActive: Boolean = true,

    @Column(name = "account_number", unique = true)
    val accountNumber: String
)
