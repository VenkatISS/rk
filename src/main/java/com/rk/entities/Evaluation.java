package com.rk.entities;

public class Evaluation {
    private SwimTrainee swimTrainee;
    private int rating;
    private SwimTrainingSession session;
    public Evaluation(SwimTrainee swimTrainee, int rating, SwimTrainingSession session) {
        this.swimTrainee = swimTrainee;
        this.rating = rating;
        this.session = session;
    }

    public SwimTrainee getSwimTrainee() {
        return swimTrainee;
    }

    public void setSwimTrainee(SwimTrainee swimTrainee) {
        this.swimTrainee = swimTrainee;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public SwimTrainingSession getTrainingSession() {
        return session;
    }

    public void setTrainingSession(SwimTrainingSession session) {
        this.session = session;
    }
}
