package com.adrjan.gymtracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSessionForm {

    private List<@Valid ExerciseForm> trainingSessionForm;
}
