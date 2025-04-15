package com.banking.bankingsystem.controller

import com.banking.bankingsystem.repository.TransactionRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transactions/v1")
class TransactionController(private val transactionRepository: TransactionRepository) {

    @GetMapping("/{accountNumber}")
    fun getTransactions(@PathVariable accountNumber: String): ResponseEntity<Any> {
        val txns = transactionRepository.findBySourceAccountOrDestinationAccount(accountNumber, accountNumber)
        return ResponseEntity.ok(txns)
    }
}
