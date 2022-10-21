package com.adrjan.gymtracker.controllers;

import com.adrjan.gymtracker.entity.Exercise;
import com.adrjan.gymtracker.entity.ExerciseSerie;
import com.adrjan.gymtracker.entity.ExerciseSession;
import com.adrjan.gymtracker.entity.TrainingSession;
import com.adrjan.gymtracker.model.TrainingSessionForm;
import com.adrjan.gymtracker.repositories.ExerciseRepository;
import com.adrjan.gymtracker.repositories.ExerciseSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/add-training")
public class AddTrainingController {

    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    ExerciseSerieRepository exerciseSerieRepository;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("exercisesList", exerciseRepository.findAll());
        return "add-training";
    }

    @PostMapping
    public String postData(@ModelAttribute TrainingSessionForm trainingSessionForm) {
        TrainingSession trainingSession = new TrainingSession();
        List<ExerciseSerie> exerciseSerieList = new ArrayList<>();

        trainingSessionForm.getTrainingSessionForm().forEach(
                exerciseForm -> {
                    ExerciseSession tempExerciseSession = ExerciseSession.builder().trainingSession(trainingSession).build();
                    Exercise exercise = Exercise.builder().id(exerciseForm.getExerciseId()).name("nothing").build();

                    for (int i = 0; i < exerciseForm.getRepForm().size(); i++) {
                        exerciseSerieList.add(
                                ExerciseSerie.builder()
                                        .id(0)
                                        .exercise(exercise)
                                        .reps(exerciseForm.getRepForm().get(i).getReps())
                                        .weight(exerciseForm.getWeightForm().get(i).getWeight())
                                        .exerciseSession(tempExerciseSession)
                                        .build());
                    }
                }
        );
        exerciseSerieRepository.saveAll(exerciseSerieList);
        return "redirect:/add-training";
    }
}
