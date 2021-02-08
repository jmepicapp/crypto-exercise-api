package com.jmaguero.crypto.exercise.api.repository;

import com.jmaguero.crypto.exercise.api.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
