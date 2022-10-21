package com.adrjan.gymtracker.controllers;

import com.adrjan.gymtracker.entity.Exercise;
import com.adrjan.gymtracker.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping
    public String showExercise(Model model) {
        List<Exercise> exercises = new ArrayList<>();

        exerciseRepository.findAll().forEach(exercises::add);
        model.addAttribute("exercises", exercises);
        if(!model.containsAttribute("exercise"))
            model.addAttribute("exercise", new Exercise());

        return "exercise";
    }

    @PostMapping
    public String addExercise(@Valid Exercise exercise, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exercise", bindingResult);
            redirectAttributes.addFlashAttribute("exercise", exercise);
        }
        else {
            exerciseRepository.save(exercise);
        }
        return "redirect:/exercise";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExercise(@PathVariable int id) {
        exerciseRepository.deleteById(id);
        return "redirect:/exercise";
    }
}
