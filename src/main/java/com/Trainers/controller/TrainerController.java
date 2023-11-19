package com.Trainers.controller;

import com.Trainers.model.Session;
import com.Trainers.model.Trainer;
import com.Trainers.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;}


    @GetMapping("/findall")
    public ResponseEntity<List<Trainer>> findAll() {
        List<Trainer> trainers = trainerService.findAllTrainers();
        return ResponseEntity.ok(trainers);
    }




        @PostMapping("/create")
                public ResponseEntity<Trainer> createNewTrainer(@RequestBody Trainer trainer)
        {
            Trainer createdTrainer = trainerService.createNewTrainer(trainer);
            return ResponseEntity.status(201).body(createdTrainer);
        }

}
