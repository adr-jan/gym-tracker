package com.adrjan.gymtracker.repositories;

import com.adrjan.gymtracker.entity.TrainingSession;
import org.springframework.data.repository.CrudRepository;

public interface TrainingsRepository extends CrudRepository<TrainingSession, Integer> {
}
