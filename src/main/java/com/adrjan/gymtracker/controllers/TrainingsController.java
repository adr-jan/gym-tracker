package com.adrjan.gymtracker.controllers;

import com.adrjan.gymtracker.entity.TrainingSession;
import com.adrjan.gymtracker.repositories.TrainingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/trainings")
public class TrainingsController {

    @Autowired
    TrainingsRepository trainingsRepository;

    @GetMapping
    public String showPage(Model model) {
        baseShowPage(model);
        return "trainings";
    }

    @GetMapping("/{id}")
    public String showPage(@PathVariable("id") int id, Model model) {
        baseShowPage(model);

        model.addAttribute("trainingSession", trainingsRepository.findById(id).orElseGet(TrainingSession::new));

        return "trainings";
    }

    @PostMapping
    public String selectTraining(@ModelAttribute TrainingSession selectedTraining, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("training", selectedTraining);
        return "redirect:/trainings";
    }

    private void baseShowPage(Model model) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E     dd-MM-yyyy HH:mm");
        Map<TrainingSession, String> trainings = new LinkedHashMap<>();

        trainingsRepository.findAllByOrderByCreatedAtDesc().forEach(
                training -> trainings.put(training, simpleDateFormat.format(training.getCreatedAt()))
        );

        model.addAttribute("trainings", trainings);
    }
}
