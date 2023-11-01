package com.Trainers.repo;

import com.Trainers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface User_Repo extends JpaRepository <User, Long>
{

}
