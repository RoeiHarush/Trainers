package com.Trainers.service.impl;

import com.Trainers.exceptions.TrainerNotFoundException;
import com.Trainers.model.Review;
import com.Trainers.model.Session;
import com.Trainers.model.Trainer;
import com.Trainers.repo.Review_Repo;
import com.Trainers.repo.Session_Repo;
import com.Trainers.repo.Trainer_Repo;
import com.Trainers.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class TrainerServiceImpl implements TrainerService
{

    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);
    private final Trainer_Repo trainerRepo;
    private final Review_Repo reviewRepo;
    private final Session_Repo sessionRepo;

    @Autowired
    public TrainerServiceImpl(Trainer_Repo trainerRepo, Review_Repo reviewRepo, Session_Repo sessionRepo) {
        this.trainerRepo = trainerRepo;
        this.reviewRepo = reviewRepo;
        this.sessionRepo = sessionRepo;
    }


    @Override
    public Trainer createNewTrainer(Trainer trainer) {
        logger.info("Creating user: {}", trainer.getUser_Name());
        logger.info("Email: {}", trainer.getEmail());
        logger.info("First Name: {}", trainer.getFirst_Name());
        logger.info("Last Name: {}", trainer.getLast_Name());
        logger.info("password : {}" , trainer.getPassword());
        Trainer savedTrainer = trainerRepo.save(trainer);

        if (savedTrainer.getId() != null) {
            logger.info("Trainer created successfully. User ID: {}", savedTrainer.getId());
        } else {
            logger.error("Failed to create user.");
        }

        return savedTrainer;

    }



    @Override
    public void deleteTrainerById(Long id) {
            logger.info("Deleting trainer with ID: {}", id);
            trainerRepo.deleteById(id);
    }

    @Override
    public Optional<Trainer> findById(Long trainerId) {
        logger.info("Fetching trainer by ID: {}", trainerId);
        return trainerRepo.findById(trainerId);
    }

    @Override
    public List<Trainer> findAllTrainers() {
        logger.info("list of all trainers : ");
        return trainerRepo.findAll();
    }

    @Override
    public Trainer updateTrainer(Long trainerId, Trainer updatedTrainer) {
        logger.info("Updating trainer with ID: {}", trainerId);
        Optional<Trainer> optionalTrainer = trainerRepo.findById(trainerId);

        if (optionalTrainer.isPresent()) {
            Trainer existingTrainer = optionalTrainer.get();
            existingTrainer.setFirst_Name(updatedTrainer.getFirst_Name());
            existingTrainer.setBio(updatedTrainer.getBio());
            existingTrainer.setAvailability(updatedTrainer.getAvailability());

            return trainerRepo.save(existingTrainer);
        } else {
            throw new TrainerNotFoundException("Trainer not found with id: " + trainerId);
        }
    }
    @Override
    public List<Trainer> findTrainersByLocation(String location) {
        logger.info("Finding trainers by location: {}", location);
        return trainerRepo.findByLocation(location);
    }

    @Override
    public List<Trainer> findTrainersByCategory(String category) {
        logger.info("Finding trainers by specialization: {}", category);
        return trainerRepo.findByCategory(category);
    }

    @Override
    public void addSessionToTrainer(Long trainerId, Session session) {
        logger.info("Adding session to trainer with ID: {}", trainerId);
        Optional<Trainer> optionalTrainer = trainerRepo.findById(trainerId);

        if (optionalTrainer.isPresent()) {
            Trainer trainer = optionalTrainer.get();
            // Add the session to the trainer's sessions list
            trainer.getSessions().add(session);

            trainerRepo.save(trainer);
        } else {
            throw new TrainerNotFoundException("Trainer not found with id: " + trainerId);
        }
    }

    @Override
    public List<Session> getTrainerSessions(Long trainerId) {
        logger.info("Fetching sessions for trainer with ID: {}", trainerId);
        Optional<Trainer> optionalTrainer = trainerRepo.findById(trainerId);

        if (optionalTrainer.isPresent()) {
            Trainer trainer = optionalTrainer.get();
            return trainer.getSessions();
        } else {
            throw new TrainerNotFoundException("Trainer not found with id: " + trainerId);
        }
    }

    @Override
    public List<Review> getTrainerReviews(Long trainerId) {
        logger.info("Fetching reviews for trainer with ID: {}", trainerId);
        return reviewRepo.getReviewsByTrainerId(trainerId);
    }


}
