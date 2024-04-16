package com.rk.strategies;

import com.rk.entities.Evaluation;
import com.rk.entities.SwimTrainer;
import com.rk.service.SwimAcademy;
import com.rk.utils.Helper;

import java.util.List;

public class GenerateSwimTrainersEvaluationStrategy implements GenerateEvaluationStrategy {
    @Override
    public void generateEvaluation(SwimAcademy swimAcademy) {
        List<Evaluation> evaluation = swimAcademy.getEvaluation();
        List<SwimTrainer> trainers = swimAcademy.getSwimTrainers();
        System.out.println("---------------------------------------------");
        System.out.println("Swim Trainers Evaluation");
        System.out.println("---------------------------------------------");
        System.out.println("Number of swim trainers: " + trainers.size());
        System.out.println("Swim Trainers: ");
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Average Rating","Global Rating");
        for (SwimTrainer trainer : trainers) {
            double rating = getAverageRating(evaluation, trainer);
            System.out.printf("%-20s%-20s%-20s\n", trainer.getName(), rating, Helper.getRatingString((int) rating));
        }
        System.out.println("---------------------------------------------");
    }

    private double getAverageRating(List<Evaluation> evaluations, SwimTrainer trainer) {
        double sum = 0;
        int count = 0;
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getTrainingSession().getSwimTrainer().equals(trainer)) {
                sum += evaluation.getRating();
                count++;
            }
        }
        return sum / count;
    }
}
