package com.Trainers.service.impl;

import com.Trainers.dto.SessionDTO;
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
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {
    private final Session_Repo sessionRepo;
    private final Trainer_Repo trainerRepo;


    public SessionServiceImpl(Session_Repo sessionRepo, Trainer_Repo trainerRepo) {
        this.sessionRepo = sessionRepo;
        this.trainerRepo = trainerRepo;
    }




    public List<SessionDTO> findAllSession() {
        List<Session> sessions = sessionRepo.findAll();
        return sessions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }




    public SessionDTO convertToDTO(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(session.getId());
        sessionDTO.setStartTime(session.getStartTime());
        sessionDTO.setEndTime(session.getEndTime());
        sessionDTO.setTrainerId(session.getTrainer().getId()); // Assuming Trainer has an 'id' field
        return sessionDTO;
    }



    @Override
    public List<Session> findAllSessions() {
        return  sessionRepo.findAll();
    }

    @Override
    public Session createSession(LocalDateTime startTime, LocalDateTime endTime, Long trainerId) {
        if (trainerId == null) {

            throw new IllegalArgumentException("Trainer ID must not be null");
        }

        Session session = new Session();
        Trainer trainer = trainerRepo.findById(trainerId)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer not found with ID: " + trainerId));

        session.setTrainer(trainer);
        session.setStartTime(startTime);
        session.setEndTime(endTime);

        return sessionRepo.save(session);
    }


    @Override
    public Session updateSession(Long sessionId, Session updatedSession) {
        Session existingSession = sessionRepo.findById(sessionId)
                .orElse(null);

        if (existingSession != null) {

            existingSession.setStartTime(updatedSession.getStartTime());
            existingSession.setEndTime(updatedSession.getEndTime());

            return sessionRepo.save(existingSession);
        } else {
            return null;
        }
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
