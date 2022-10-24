package com.adrjan.gymtracker.controllers;

import com.adrjan.gymtracker.entity.TrainingSession;
import com.adrjan.gymtracker.repositories.TrainingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/trainings")
public class TrainingsController {

    @Autowired
    TrainingsRepository trainingsRepository;

    @GetMapping
    public String showPage(Model model) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E     dd-MM-yyyy HH:mm");
        Map<TrainingSession, String> trainings = new HashMap<>();

        trainingsRepository.findAll().forEach(
                training -> trainings.put(training, simpleDateFormat.format(training.getCreatedAt()))
        );

        model.addAttribute("trainings", trainings);
        return "trainings";
    }

    @PostMapping
    public String selectTraining(@ModelAttribute TrainingSession selectedTraining, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("training", selectedTraining);
        return "redirect:/trainings";
    }
}
