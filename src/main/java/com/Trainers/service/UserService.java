package com.Trainers.service;

import com.Trainers.model.Booking;
import com.Trainers.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService
{
    User createNewUser (User appUser);

    void deleteUserById(Long id);
    Optional<User> findById(Long userId);
    public List<User> findAllUsers();
    User updateUser(Long userId, User updatedUser);
    List<Booking> getUserBookings(Long userId);
    void bookToSession(Long userId, Long sessionId);



}
