package com.Trainers.repo;

import com.Trainers.model.Session;
import com.Trainers.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Session_Repo extends JpaRepository<Session,Long>
{
   List<Session> findByTrainerId(Long trainerId);
}
