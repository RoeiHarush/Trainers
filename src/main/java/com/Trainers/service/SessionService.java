package com.Trainers.service;

import com.Trainers.dto.SessionDTO;
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
    List<Session> findAllSessions();
    public List<SessionDTO> findAllSession();
    public SessionDTO convertToDTO(Session session);

    Session createSession(LocalDateTime startTime, LocalDateTime endTime, Long trainerId);

    // Update session details
    Session updateSession(Long sessionId, Session updatedSession);

    // Cancel a session
    void cancelSession(Long sessionId);

    // Get session details by ID
    Optional<Session> getSessionById(Long sessionId);

    // Get sessions for a specific trainer
    List<Session> getSessionsByTrainer(Long trainerId);

}
