package com.ul.vrs.repository;

import com.ul.vrs.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Add specific query methods for Customer if needed
}
