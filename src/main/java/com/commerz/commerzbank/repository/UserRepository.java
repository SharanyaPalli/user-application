package com.commerz.commerzbank.repository;

import com.commerz.commerzbank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.processed = false AND u.processingStartedAt IS NULL")
    List<User> findUnprocessedUsers();

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.processingStartedAt IS NULL")
    Optional<User> findByIdAndProcessingStartedAtIsNull(@Param("id") Long id);
}