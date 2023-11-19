package com.Trainers.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO
{
    private Long trainerId;
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    }

