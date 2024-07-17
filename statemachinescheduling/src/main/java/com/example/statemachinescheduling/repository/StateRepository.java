package com.example.statemachinescheduling.repository;

import com.example.statemachinescheduling.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {
    void deleteByCreatedAtBefore(LocalDateTime cutoff);
    // You can define custom database queries here if needed
}
