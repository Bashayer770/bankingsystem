package com.banking.bankingsystem.controller

import com.banking.bankingsystem.model.KycEntity
import com.banking.bankingsystem.repository.KycRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/v1/kyc")
class KycController(private val kycRepository: KycRepository) {

    @PostMapping
    fun saveOrUpdateKyc(@RequestBody kyc: KycEntity): ResponseEntity<KycEntity> {
        val saved = kycRepository.save(kyc)
        return ResponseEntity.ok(saved)
    }

    @GetMapping("/{userId}")
    fun getKyc(@PathVariable userId: Long): ResponseEntity<Any> {
        val kyc = kycRepository.findById(userId)
        return if (kyc.isPresent) {
            ResponseEntity.ok(kyc.get())
        } else {
            ResponseEntity.status(404).body(" KYC info not found for userId $userId")
        }
    }
}
