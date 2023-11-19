package com.Trainers.payloads;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SessionRequest
{

        private LocalDateTime startTime;
        private LocalDateTime endTime;

        private Long trainerId;

        // getters and setters

}
