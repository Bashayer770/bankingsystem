package com.banking.bankingsystem.repository

import com.banking.bankingsystem.model.KycEntity
import org.springframework.data.jpa.repository.JpaRepository

interface KycRepository : JpaRepository<KycEntity, Long>
