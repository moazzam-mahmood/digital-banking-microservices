package com.demoproject.accounts.repository;

import com.demoproject.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerId(long customerId);

    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteByCustomerId(long customerId);
}
