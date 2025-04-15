package com.banking.bankingsystem.controller

import com.banking.bankingsystem.model.AccountEntity
import com.banking.bankingsystem.repository.AccountRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.banking.bankingsystem.model.TransactionEntity
import com.banking.bankingsystem.repository.TransactionRepository


data class TransferRequest(
    val sourceAccountNumber: String,
    val destinationAccountNumber: String,
    val amount: Double
)

data class TransferResponse(
    val newBalance: Double
)


@RestController
@RequestMapping("/accounts/v1/accounts")
class AccountController(private val accountRepository: AccountRepository, private val transactionRepository: TransactionRepository) {

    @PostMapping
    fun createAccount(@RequestBody account: AccountEntity): ResponseEntity<AccountEntity> {
        val saved = accountRepository.save(account)
        return ResponseEntity.ok(saved)
    }

    @GetMapping
    fun getAllAccounts(): ResponseEntity<List<AccountEntity>> {
        val accounts = accountRepository.findAll()
        return ResponseEntity.ok(accounts)
    }

    @PostMapping("/{accountNumber}/close")
    fun closeAccount(@PathVariable accountNumber: String): ResponseEntity<String> {
        val account = accountRepository.findAll().find { it.accountNumber == accountNumber }

        if (account == null) {
            return ResponseEntity.notFound().build()
        }

        val closedAccount = account.copy(isActive = false)
        accountRepository.save(closedAccount)

        return ResponseEntity.ok(" Account $accountNumber closed successfully.")
    }

    @PostMapping("/transfer")
    fun transferFunds(@RequestBody request: TransferRequest): ResponseEntity<Any> {
        val source = accountRepository.findAll().find { it.accountNumber == request.sourceAccountNumber }
        val destination = accountRepository.findAll().find { it.accountNumber == request.destinationAccountNumber }

        if (source == null || destination == null) {
            return ResponseEntity.badRequest().body(" One or both accounts not found.")
        }

        if (!source.isActive || !destination.isActive) {
            return ResponseEntity.badRequest().body(" One or both accounts are inactive.")
        }

        if (source.balance < request.amount) {
            return ResponseEntity.badRequest().body(" Insufficient funds.")
        }

        val updatedSource = source.copy(balance = source.balance - request.amount)
        val updatedDestination = destination.copy(balance = destination.balance + request.amount)

        accountRepository.save(updatedSource)
        accountRepository.save(updatedDestination)

        val transaction = TransactionEntity(
            sourceAccount = request.sourceAccountNumber,
            destinationAccount = request.destinationAccountNumber,
            amount = request.amount,
            type = "TRANSFER"
        )

        transactionRepository.save(transaction)

        return ResponseEntity.ok(TransferResponse(newBalance = updatedSource.balance))
    }




}
