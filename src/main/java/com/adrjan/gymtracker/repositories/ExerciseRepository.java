package com.adrjan.gymtracker.repositories;

import com.adrjan.gymtracker.entity.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

}
