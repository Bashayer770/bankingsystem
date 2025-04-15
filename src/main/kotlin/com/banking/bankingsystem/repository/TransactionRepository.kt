package com.banking.bankingsystem.repository

import com.banking.bankingsystem.model.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository


interface TransactionRepository : JpaRepository<TransactionEntity, Long> {
    fun findBySourceAccountOrDestinationAccount(
        source: String,
        destination: String
    ): List<TransactionEntity>
}
