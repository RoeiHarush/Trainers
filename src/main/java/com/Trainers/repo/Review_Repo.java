package com.Trainers.repo;

import com.Trainers.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface Review_Repo extends JpaRepository <Review,Long>
{
    List<Review> getReviewsByTrainerId(Long trainerId);
}
