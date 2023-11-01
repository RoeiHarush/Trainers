package com.Trainers.repo;

import com.Trainers.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Session_Repo extends JpaRepository<Session,Long> {
}
