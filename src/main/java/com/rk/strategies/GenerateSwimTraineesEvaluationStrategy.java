package com.rk.strategies;

import com.rk.entities.SwimTrainee;
import com.rk.service.SwimAcademy;

import java.util.List;

public class GenerateSwimTraineesEvaluationStrategy implements GenerateEvaluationStrategy {
    @Override
    public void generateEvaluation(SwimAcademy swimAcademy) {
        List<SwimTrainee> trainees = swimAcademy.getSwimTrainees();
        System.out.println("---------------------------------------------");
        System.out.println("Swim Trainees Evaluation");
        System.out.println("---------------------------------------------");
        for (SwimTrainee trainee : trainees) {
            System.out.println(trainee);
        }
        System.out.println("---------------------------------------------");
    }
}
