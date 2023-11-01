package com.Trainers.service;

import com.Trainers.model.Review;
import com.Trainers.model.Session;
import com.Trainers.model.Trainer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TrainerService {

    Trainer createNewTrainer(Trainer trainer);

    void deleteTrainerById(Long id);

    Optional<Trainer> findById(Long trainerId);

    List<Trainer> findAllTrainers();

    Trainer updateTrainer(Long trainerId, Trainer updatedTrainer);

      List<Trainer> findTrainersByLocation(String location);

    List<Trainer> findTrainersByCategory(String category);

    void addSessionToTrainer(Long trainerId, Session session);

   List<Session> getTrainerSessions(Long trainerId);

  List<Review> getTrainerReviews(Long trainerId);
}


