package com.Trainers.service.impl;

import com.Trainers.exceptions.TrainerNotFoundException;
import com.Trainers.model.Session;
import com.Trainers.model.Trainer;
import com.Trainers.repo.Session_Repo;
import com.Trainers.repo.Trainer_Repo;
import com.Trainers.service.SessionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {
    private final Session_Repo sessionRepo;
    private final Trainer_Repo trainerRepo;

    @Autowired
    public SessionServiceImpl(Session_Repo sessionRepo, Trainer_Repo trainerRepo) {
        this.sessionRepo = sessionRepo;
        this.trainerRepo = trainerRepo;
    }

    @Override
    public Session createSession(LocalDateTime startTime, LocalDateTime endTime, Session session, Long trainerId) {

        Trainer trainer = trainerRepo.findById(trainerId)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer not found with ID: " + trainerId));

        session.setTrainer(trainer);
        session.setStartTime(startTime);
        session.setEndTime(endTime);


        return sessionRepo.save(session);
    }


    @Override
    public Session updateSession(Long sessionId, Session updatedSession) {
        return null;
    }

    @Override
    public void cancelSession(Long sessionId) {
        sessionRepo.deleteById(sessionId);

    }

    @Override
    public Optional<Session> getSessionById(Long sessionId) {
        return sessionRepo.findById(sessionId);
    }

    @Override
    public List<Session> getSessionsByTrainer(Long trainerId) {
        return sessionRepo.findByTrainerId(trainerId);

    }
}
