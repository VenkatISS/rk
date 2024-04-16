package com.rk.service;

import com.rk.entities.SwimTrainee;
import com.rk.entities.SwimTrainer;
import com.rk.entities.SwimTrainingSession;
import com.rk.entities.TrainingDay;
import com.rk.exceptions.*;

public interface ISwimAcademy {

    void viewSwimSlotsByDay(TrainingDay day);
    void viewSwimSlotsByGrade(int grade);
    void viewSwimSlotsBySwimTrainer(SwimTrainer trainer);

    void bookTrainingSession(SwimTrainee trainee, SwimTrainingSession session) throws NotEligibleForTrainingSessionException, TrainingSessionAlreadyBookedException, TrainingSessionIsFullException;
    void cancelTrainingSession(SwimTrainee trainee, SwimTrainingSession session) throws TrainingSessionNotBookedException, TrainingSessionIsAttendedException;
    void attendTrainingSession(SwimTrainee trainee, SwimTrainingSession session) throws TrainingSessionNotBookedException;

    void assignSwimTrainerToTrainingSession(SwimTrainer trainer, SwimTrainingSession session) throws SwimmingSessionAlreadyHasSwimTrainerException;

    void addSwimTrainee(SwimTrainee trainee) throws SwimTraineeAlreadyExistsException;
    void removeSwimTrainee(SwimTrainee trainee);

    void addSwimTrainer(SwimTrainer trainer) throws SwimTrainerAlreadyExistsException;
    void removeSwimTrainer(SwimTrainer trainer);

    void addTrainingSession(SwimTrainingSession session);
    void removeTrainingSession(SwimTrainingSession session);

    void writeEvaluation(SwimTrainee trainee, SwimTrainingSession session, int rating) throws RatingOutOfRangeException, WriteEvaluationException;

    void generateSwimTraineeReport();
    void generateSwimTrainerReport();
}
