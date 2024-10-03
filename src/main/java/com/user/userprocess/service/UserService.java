package com.user.userprocess.service;

import com.user.userprocess.entity.User;
import com.user.userprocess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUserOrUpdate(User user) {
        Optional<User> existingUserId = userRepository.findByUserId(user.getUserId());

        if (existingUserId.isPresent()) {
            User existingUser = existingUserId.get();
            boolean isMobileNumberChanged = !existingUser.getMobileNumber().equals(user.getMobileNumber());
            boolean isNameChanged = !existingUser.getName().equals(user.getName());
            boolean isEmailChanged = !existingUser.getEmail().equals(user.getEmail());

            if (isMobileNumberChanged || isNameChanged || isEmailChanged) {
                existingUser.setMobileNumber(user.getMobileNumber());
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                userRepository.save(existingUser);
                return "User details updated successfully";
            } else {
                return "User Details already exist";
            }
        } else {
            userRepository.save(user);
            return "User added successfully";
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Transactional
    @Scheduled(fixedRate = 5000)
    public void processUsers() {
        List<User> unprocessedUsers = userRepository.findByProcessedFalse();
        for (User user : unprocessedUsers) {
            try {
                System.out.println("Processing user: " + user.getName() + " " + user.getMobileNumber() +  " " + user.getEmail() + " " +user.isProcessed());
                user.setProcessed(true);
                userRepository.save(user);

            } catch (OptimisticLockingFailureException e) {
                System.out.println("Processing user: " + user.getName() + " " + user.getMobileNumber() +  " " + user.getEmail() + " " +user.isProcessed());
            }
        }
    }
}
