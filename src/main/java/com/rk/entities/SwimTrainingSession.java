package com.rk.entities;

import java.util.ArrayList;
import java.util.List;

public class SwimTrainingSession {
    private int id;
    private SwimSlot swimSlot;
    private SwimTrainer swimTrainer;
    private TrainingDay day;
    private int grade;
    private List<SwimTrainee> attendees;

    // only for testing
    public SwimTrainingSession(int id) {
        this.id = id;
        this.grade = 2;
        this.attendees = new ArrayList<>();
    }
    // only for testing
    public SwimTrainingSession(int id, int grade) {
        this.id = id;
        this.grade = grade;
        this.attendees = new ArrayList<>();
    }

    public SwimTrainingSession(int id, SwimSlot swimSlot, TrainingDay day, int grade) {
        this.id = id;
        this.swimSlot = swimSlot;
        this.day = day;
        this.grade = grade;
        this.attendees = new ArrayList<>();
    }

    public SwimTrainingSession(int id, SwimSlot swimSlot, SwimTrainer swimTrainer, TrainingDay day, int grade) {
        this.id = id;
        this.swimSlot = swimSlot;
        this.swimTrainer = swimTrainer;
        this.day = day;
        this.grade = grade;
        this.attendees = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFull() {
        return this.attendees.size() == 4;
    }

    public void addAttendee(SwimTrainee swimTrainee) {
        this.attendees.add(swimTrainee);
    }

    public void removeAttendee(SwimTrainee swimTrainee) {
        this.attendees.remove(swimTrainee);
    }

    public SwimSlot getSwimSlots() {
        return swimSlot;
    }

    public void setSwimSlots(SwimSlot swimSlot) {
        this.swimSlot = swimSlot;
    }

    public SwimTrainer getSwimTrainer() {
        return swimTrainer;
    }

    public void setSwimTrainer(SwimTrainer swimTrainer) {
        this.swimTrainer = swimTrainer;
    }

    public TrainingDay getDay() {
        return day;
    }

    public void setDay(TrainingDay day) {
        this.day = day;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<SwimTrainee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<SwimTrainee> attendees) {
        this.attendees = attendees;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SwimTrainingSession other = (SwimTrainingSession) obj;
        return id == other.id;
    }
}
