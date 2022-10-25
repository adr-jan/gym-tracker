package com.adrjan.gymtracker.repositories;

import com.adrjan.gymtracker.entity.TrainingSession;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingsRepository extends CrudRepository<TrainingSession, Integer> {
    List<TrainingSession> findAllByOrderByCreatedAtDesc();
}
