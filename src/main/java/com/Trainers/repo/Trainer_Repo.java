package com.Trainers.repo;

import com.Trainers.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Trainer_Repo extends JpaRepository<Trainer, Long> {
    List<Trainer> findByLocation(String location);

     List<Trainer> findByCategory(String category);
}
