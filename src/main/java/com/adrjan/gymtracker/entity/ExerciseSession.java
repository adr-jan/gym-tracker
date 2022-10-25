package com.adrjan.gymtracker.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ExerciseSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
    @OneToMany(mappedBy = "exerciseSession")
    private List<ExerciseSerie> exerciseSerieList = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_session_id")
    private TrainingSession trainingSession;
}
