package com.Trainers.repo;

import com.Trainers.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Booking_Repo extends JpaRepository<Booking,Long> {
    List<Booking> findByUserId(Long userId);
}
