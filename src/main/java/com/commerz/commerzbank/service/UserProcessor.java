package com.commerz.commerzbank.service;

import com.commerz.commerzbank.entity.User;
import com.commerz.commerzbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserProcessor {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedRate = 5000)
    public void processUsers() {
        List<User> users = userRepository.findUnprocessedUsers();
        for (User user : users) {
            System.out.println("Processing user: " + user.getEmail());
            if (acquireLock(user)) {
                processUser(user);
            }
        }

    }

    private boolean acquireLock(User user) {
        Optional<User> lockedUser = userRepository.findByIdAndProcessingStartedAtIsNull(user.getId());
        if (lockedUser.isPresent()) {
            user.setProcessingStartedAt(LocalDateTime.now());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private void processUser(User user) {
        try {
            // Implement your business logic here
            // Mark user as processed after successful processing
            user.setProcessed(true);
        } catch (Exception e) {
            // Handle any exceptions during processing
        } finally {
            user.setProcessingStartedAt(null);  // Clear the lock regardless of success or failure
            userRepository.save(user);
        }
    }
}