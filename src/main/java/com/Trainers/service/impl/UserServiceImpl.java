package com.Trainers.service.impl;

import com.Trainers.model.Booking;
import com.Trainers.model.Session;
import com.Trainers.model.User;
import com.Trainers.repo.Booking_Repo;
import com.Trainers.repo.Session_Repo;
import com.Trainers.repo.User_Repo;
import com.Trainers.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final User_Repo userRepo;
    private final Booking_Repo bookingRepo;
    private final Session_Repo sessionRepo;


    @Autowired
    public UserServiceImpl(User_Repo userRepo, Booking_Repo bookingRepo, Session_Repo sessionRepo) {
        this.userRepo = userRepo;
        this.bookingRepo = bookingRepo;
        this.sessionRepo = sessionRepo;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User createNewUser(User user) {
        logger.info("Creating user: {}", user.getUser_Name());
        logger.info("Email: {}", user.getEmail());
        logger.info("First Name: {}", user.getFirst_Name());
        logger.info("Last Name: {}", user.getLast_Name());
        User savedUser = userRepo.save(user);

        if (savedUser.getId() != null) {
            logger.info("User created successfully. User ID: {}", savedUser.getId());
        } else {
            logger.error("Failed to create user.");
        }

        return savedUser;

    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userRepo.deleteById(id);
            logger.info("User with ID {} deleted successfully.", id);
        } catch (Exception e) {
            logger.error("Error deleting user with ID {}: {}", id, e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        return null;
    }

    @Override

    public List<Booking> getUserBookings(Long userId) {
        logger.info("Fetching bookings for user with ID: {}", userId);

        List<Booking> bookings = bookingRepo.findByUserId(userId);

        if (bookings.isEmpty()) {
            logger.info("No bookings found for user with ID: {}", userId);
        } else {
            logger.info("Found {} bookings for user with ID: {}", bookings.size(), userId);
        }

        return bookings;
    }

    @Override
    public void bookToSession(Long userId, Long sessionId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        Optional<Session> optionalSession = sessionRepo.findById(sessionId);

        if (optionalUser.isPresent() && optionalSession.isPresent()) {
            User user = optionalUser.get();
            Session session = optionalSession.get();

            // Add the session to the user's bookings
            user.getBookings().add(new Booking(user, session));

            // Update the user entity in the database
            userRepo.save(user);
        } else {
            // Handle cases where the user or session is not found
            throw new EntityNotFoundException("User or Session not found with provided IDs");
        }
    }
}
