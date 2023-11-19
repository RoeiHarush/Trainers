package com.Trainers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "sessions")
public class Session
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;


    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String details;

    public Session(Trainer trainer, LocalDateTime startTime, LocalDateTime endTime) {
        this.trainer = trainer;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
