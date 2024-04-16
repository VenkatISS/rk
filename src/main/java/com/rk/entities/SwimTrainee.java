package com.rk.entities;

import com.rk.exceptions.SwimTraineeAgeException;

import java.util.ArrayList;
import java.util.List;

public class SwimTrainee {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int grade;
    private List<SwimTrainingSession> trainingSessionsBooked;
    private List<SwimTrainingSession> trainingSessionsAttended;
    private List<SwimTrainingSession> trainingSessionsCancelled;

    public SwimTrainee(String name, String gender , int age, String emergencyContact, int grade) throws SwimTraineeAgeException {
        if(age < 4 || age > 11) {
            throw new SwimTraineeAgeException("Age should be between 4 and 11");
        }
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.grade = grade;
        this.trainingSessionsBooked = new ArrayList<>();
        this.trainingSessionsAttended = new ArrayList<>();
        this.trainingSessionsCancelled = new ArrayList<>();
    }

    public boolean isTrainingSessionAttended(SwimTrainingSession session) {
        return trainingSessionsAttended.contains(session);
    }

    public boolean isTrainingSessionBooked(SwimTrainingSession session) {
        return trainingSessionsBooked.contains(session);
    }

    public boolean isEligibleForTrainingSession(SwimTrainingSession session){
        return grade == session.getGrade() || grade == session.getGrade() - 1;
    }

    public void bookTrainingSession(SwimTrainingSession session) {
        trainingSessionsBooked.add(session);
    }

    public void attendTrainingSession(SwimTrainingSession session) {
        trainingSessionsBooked.remove(session);
        trainingSessionsAttended.add(session);
        setGrade(session.getGrade());
    }

    public void cancelTrainingSession(SwimTrainingSession session) {
        trainingSessionsBooked.remove(session);
        trainingSessionsCancelled.add(session);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<SwimTrainingSession> getTrainingSessionsBooked() {
        return trainingSessionsBooked;
    }

    public List<SwimTrainingSession> getTrainingSessionsAttended() {
        return trainingSessionsAttended;
    }


    public List<SwimTrainingSession> getTrainingSessionsCancelled() {
        return trainingSessionsCancelled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwimTrainee swimTrainee = (SwimTrainee) o;
        return name.equals(swimTrainee.name);
    }


    @Override
    public String toString() {
        String sb = "Name: " + name + "\n" +
                "Gender: " + gender + "\n" +
                "Age: " + age + "\n" +
                "Emergency Contact: " + emergencyContact + "\n" +
                "Grade: " + grade + "\n" +
                "Training Sessions Booked: " + trainingSessionsBooked.size() + "\n" +
                "Training Sessions Attended: " + trainingSessionsAttended.size() + "\n" +
                "Training Sessions Cancelled: " + trainingSessionsCancelled.size() + "\n";
        return sb;
    }
}
