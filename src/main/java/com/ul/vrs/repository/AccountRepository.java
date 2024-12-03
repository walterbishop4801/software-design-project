package com.ul.vrs.repository;

import com.ul.vrs.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Query method to find an account by username and password
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
