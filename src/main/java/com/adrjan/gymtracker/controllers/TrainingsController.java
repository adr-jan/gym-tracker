package com.adrjan.gymtracker.controllers;

import com.adrjan.gymtracker.repositories.TrainingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trainings")
public class TrainingsController {

    @Autowired
    TrainingsRepository trainingsRepository;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("trainings", trainingsRepository.findAll());
        return "trainings";
    }
}
