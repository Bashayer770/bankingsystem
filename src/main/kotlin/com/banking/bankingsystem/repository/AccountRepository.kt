package com.banking.bankingsystem.repository

import com.banking.bankingsystem.model.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountEntity, Long>
