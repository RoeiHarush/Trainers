package com.Trainers.controller;

import com.Trainers.dto.SessionDTO;
import com.Trainers.model.Session;
import com.Trainers.payloads.SessionRequest;
import com.Trainers.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/session")
public class SessionController {
    @Autowired
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
}


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Session>> getSessionById(@PathVariable Long id) {
        Optional<Session> session = sessionService.getSessionById(id);

        return ResponseEntity.ok(session);
    }



    @GetMapping("/findAll")
    public ResponseEntity<List<SessionDTO>> findAllSessions() {
    List<SessionDTO> sessionDTOs = sessionService.findAllSession();

        if (!sessionDTOs.isEmpty()) {
        return ResponseEntity.ok(sessionDTOs);
    } else {
        return ResponseEntity.noContent().build();
    }
}

    @PostMapping("/create")
    public ResponseEntity<Session> createSession(@RequestBody SessionRequest sessionRequest) {
    LocalDateTime startTime = sessionRequest.getStartTime();
    LocalDateTime endTime = sessionRequest.getEndTime();
    Long trainerId = sessionRequest.getTrainerId();
    Session createdSession = sessionService.createSession(startTime, endTime, trainerId);

        return ResponseEntity.status(201).body(createdSession);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@RequestParam Long id, @RequestBody Session updatedSession) {
        sessionService.cancelSession(id);
        return ResponseEntity.noContent().build();

}


    @PutMapping("/updatesession")
    public ResponseEntity<Session> updateSessionTime(@RequestParam Long id, @RequestBody Session updatedSession) {

        Session updated = sessionService.updateSession(id, updatedSession);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping("/findByTrainer/{trainerId}")
    public ResponseEntity<List<Session>> findSessionsByTrainer(@PathVariable Long trainerId) {
        List<Session> sessionsByTrainer = sessionService.getSessionsByTrainer(trainerId);



        if (!sessionsByTrainer.isEmpty()) {
            return ResponseEntity.ok(sessionsByTrainer);
        } else {
            return ResponseEntity.noContent().build();
        }
    }



}








