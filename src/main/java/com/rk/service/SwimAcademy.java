package com.rk.service;


import com.rk.entities.*;
import com.rk.exceptions.*;
import com.rk.strategies.GenerateEvaluationStrategy;
import com.rk.strategies.GenerateSwimTraineesEvaluationStrategy;
import com.rk.strategies.GenerateSwimTrainersEvaluationStrategy;

import java.util.ArrayList;
import java.util.List;

public class SwimAcademy implements ISwimAcademy {

    private List<SwimTrainingSession> sessions;
    private List<SwimTrainer> trainers;
    private List<SwimTrainee> trainees;
    private List<Evaluation> evaluations;
    private GenerateEvaluationStrategy generateEvaluationStrategy;

    public SwimAcademy(){
        this.sessions = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.trainees = new ArrayList<>();
        this.evaluations = new ArrayList<>();
    }

    @Override
    public void assignSwimTrainerToTrainingSession(SwimTrainer trainer, SwimTrainingSession session) throws SwimmingSessionAlreadyHasSwimTrainerException {
        if(session.getSwimTrainer() != null){
            throw new SwimmingSessionAlreadyHasSwimTrainerException("This training session already has a Swim Trainer");
        }
        session.setSwimTrainer(trainer);
    }

    @Override
    public void addSwimTrainee(SwimTrainee trainee) throws SwimTraineeAlreadyExistsException {
        if(trainees.contains(trainee)){
            throw new SwimTraineeAlreadyExistsException("Swim Trainee already exists");
        }
        trainees.add(trainee);
    }

    @Override
    public void removeSwimTrainee(SwimTrainee trainee) {
        trainees.remove(trainee);
    }

    @Override
    public void addSwimTrainer(SwimTrainer trainer) throws SwimTrainerAlreadyExistsException {
        if(trainers.contains(trainer)){
           throw new SwimTrainerAlreadyExistsException("Swim Trainer already exists");
        }
        trainers.add(trainer);
    }

    @Override
    public void removeSwimTrainer(SwimTrainer trainer) {
        trainers.remove(trainer);
    }

    @Override
    public void addTrainingSession(SwimTrainingSession session) {
        if(!sessions.contains(session)){
            sessions.add(session);
        }
    }

    @Override
    public void removeTrainingSession(SwimTrainingSession session) {
        sessions.remove(session);
    }

    @Override
    public void viewSwimSlotsByDay(TrainingDay day) {
        List<SwimTrainingSession> sessionsByDay = sessions.stream()
                .filter(session -> session.getDay() == day)
                .toList();
        printSeparator();
        System.out.println("Swim Training Sessions on " + day + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Trainer");
        for(SwimTrainingSession session : sessionsByDay){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getSwimSlots(),
                    session.getGrade(),
                    session.getSwimTrainer().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void viewSwimSlotsByGrade(int grade) {
        List<SwimTrainingSession> sessionsByGrade = sessions.stream()
                .filter(session -> session.getGrade() == grade)
                .toList();
        printSeparator();
        System.out.println("Swim Training Sessions for Grade " + grade + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Trainer");
        for(SwimTrainingSession session : sessionsByGrade){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getSwimSlots(),
                    session.getGrade(),
                    session.getSwimTrainer().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void viewSwimSlotsBySwimTrainer(SwimTrainer trainer) {
        List<SwimTrainingSession> sessionsBytrainer = sessions.stream()
                .filter(session -> session.getSwimTrainer().equals(trainer))
                .toList();
        printSeparator();
        System.out.println("Swim Training Sessions by " + trainer.getName() + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Trainer");
        for(SwimTrainingSession session : sessionsBytrainer){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getSwimSlots(),
                    session.getGrade(),
                    session.getSwimTrainer().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void bookTrainingSession(SwimTrainee trainee, SwimTrainingSession session) throws NotEligibleForTrainingSessionException, TrainingSessionAlreadyBookedException, TrainingSessionIsFullException {
        if(!trainee.isEligibleForTrainingSession(session)){
            throw new NotEligibleForTrainingSessionException("You are not eligible for this training session");
        }
        if(trainee.isTrainingSessionBooked(session)){
           throw new TrainingSessionAlreadyBookedException("You are already attending this training session");
        }
        if(session.isFull()){
           throw new TrainingSessionIsFullException("This training session is full");
        }
        session.addAttendee(trainee);
        trainee.bookTrainingSession(session);
    }

    @Override
    public void cancelTrainingSession(SwimTrainee trainee, SwimTrainingSession session) throws TrainingSessionNotBookedException, TrainingSessionIsAttendedException {
        if(!trainee.isTrainingSessionBooked(session)){
            throw new TrainingSessionNotBookedException("You are not attending this training session");
        }
        if(trainee.isTrainingSessionAttended(session)){
            throw new TrainingSessionIsAttendedException("You cannot cancel a training session you have already attended");
        }
        session.removeAttendee(trainee);
        trainee.cancelTrainingSession(session);
    }

    @Override
    public void attendTrainingSession(SwimTrainee trainee, SwimTrainingSession session) throws TrainingSessionNotBookedException {
        if(!trainee.isTrainingSessionBooked(session)){
            throw new TrainingSessionNotBookedException("You can't attend a training session you haven't booked");
        }
        trainee.attendTrainingSession(session);
    }

    @Override
    public void writeEvaluation(SwimTrainee trainee, SwimTrainingSession session, int rating) throws RatingOutOfRangeException, WriteEvaluationException {
        if(rating < 1 || rating > 5){
            throw new RatingOutOfRangeException("Rating must be between 1 and 5");
        }
        System.out.println("session.getAttendees().contains(trainee)");
        System.out.println(session.getAttendees().contains(trainee));
        if(!session.getAttendees().contains(trainee)){
           throw new WriteEvaluationException("You can't write a evaluation for a session you haven't attended");
        }
        evaluations.add(
                new Evaluation(trainee, rating, session)
        );
    }

    @Override
    public void generateSwimTraineeReport() {
        generateEvaluationStrategy = new GenerateSwimTraineesEvaluationStrategy();
        generateEvaluationStrategy.generateEvaluation(this);
    }

    @Override
    public void generateSwimTrainerReport() {
        generateEvaluationStrategy = new GenerateSwimTrainersEvaluationStrategy();
        generateEvaluationStrategy.generateEvaluation(this);
    }

    public List<SwimTrainingSession> getTrainingSessions() {
        return sessions;
    }

    public List<SwimTrainer> getSwimTrainers() {
        return trainers;
    }

    public List<SwimTrainee> getSwimTrainees() {
        return trainees;
    }

    public List<Evaluation> getEvaluation() {
        return evaluations;
    }

    private void printSeparator(){
        System.out.println("---------------------------------------------");
    }
}
