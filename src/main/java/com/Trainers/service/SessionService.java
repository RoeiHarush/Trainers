package com.Trainers.service;

import com.Trainers.model.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public interface SessionService
{

    Session createSession(LocalDateTime startTime, LocalDateTime endTime, Session session, Long trainerId);

    // Update session details
    Session updateSession(Long sessionId, Session updatedSession);

    // Cancel a session
    void cancelSession(Long sessionId);

    // Get session details by ID
    Optional<Session> getSessionById(Long sessionId);

    // Get sessions for a specific trainer
    List<Session> getSessionsByTrainer(Long trainerId);

}
