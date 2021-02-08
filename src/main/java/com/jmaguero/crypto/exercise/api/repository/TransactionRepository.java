package com.jmaguero.crypto.exercise.api.repository;

import com.jmaguero.crypto.exercise.api.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}
