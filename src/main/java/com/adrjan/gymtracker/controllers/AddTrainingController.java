package com.adrjan.gymtracker.controllers;

import com.adrjan.gymtracker.entity.Exercise;
import com.adrjan.gymtracker.entity.ExerciseSerie;
import com.adrjan.gymtracker.entity.ExerciseSeries;
import com.adrjan.gymtracker.model.ExerciseForms;
import com.adrjan.gymtracker.repositories.ExerciseRepository;
import com.adrjan.gymtracker.repositories.ExerciseSerieRepository;
import com.adrjan.gymtracker.repositories.ExerciseSeriesRepository;
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
    ExerciseSeriesRepository exerciseSeriesRepository;
    @Autowired
    ExerciseSerieRepository exerciseSerieRepository;

    @GetMapping
    public String addTraining(Model model) {
        model.addAttribute("exercisesList", exerciseRepository.findAll());
        return "add-training";
    }

    @PostMapping
    public String processAddedTraining(@ModelAttribute ExerciseForms exerciseForms) {
        List<ExerciseSerie> exerciseSerieList = new ArrayList<>();

        exerciseForms.getExerciseFormList().forEach(
                exerciseForm -> {
                    ExerciseSeries tempExerciseSeries = new ExerciseSeries();
                    Exercise exercise = Exercise.builder().id(exerciseForm.getExerciseId()).name("nothing").build();

                    for(int i = 0; i < exerciseForm.getReps().size(); i++) {
                        exerciseSerieList.add(
                                ExerciseSerie.builder()
                                        .id(0)
                                        .exercise(exercise)
                                        .reps(exerciseForm.getReps().get(i))
                                        .weight(exerciseForm.getWeights().get(i))
                                        .exerciseSeries(tempExerciseSeries)
                                        .build());
                    }
                }
        );
        exerciseSerieRepository.saveAll(exerciseSerieList);
        return "add-training";
    }
}
