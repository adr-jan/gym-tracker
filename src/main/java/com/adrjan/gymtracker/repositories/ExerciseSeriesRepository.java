package com.adrjan.gymtracker.repositories;

import com.adrjan.gymtracker.entity.ExerciseSeries;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseSeriesRepository extends CrudRepository<ExerciseSeries, Integer> {
}
