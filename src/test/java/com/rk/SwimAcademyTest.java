package com.rk;

import com.rk.entities.Evaluation;
import com.rk.entities.SwimTrainee;
import com.rk.entities.SwimTrainer;
import com.rk.entities.SwimTrainingSession;
import com.rk.exceptions.*;
import com.rk.service.SwimAcademy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SwimAcademyTest {

    private SwimAcademy swimAcademy;
    private SwimTrainee trainee;
    private SwimTrainingSession session;
    @Before
    public void setUp() throws SwimTraineeAgeException {
        swimAcademy = new SwimAcademy();
        trainee = new SwimTrainee("Surya","Male", 10,"+94771234568",1);
        session = new SwimTrainingSession(1234,2);
    }


    @Test
    public void testAddTrainee_Successful() throws SwimTraineeAlreadyExistsException, SwimTraineeAgeException {
        swimAcademy.addSwimTrainee(trainee);
        assertTrue(swimAcademy.getSwimTrainees().contains(trainee));
    }

    @Test(expected = SwimTraineeAlreadyExistsException.class)
    public void testAddTrainee_AlreadyExists() throws SwimTraineeAlreadyExistsException, SwimTraineeAgeException {
        swimAcademy.addSwimTrainee(trainee); // Adding the trainee once
        swimAcademy.addSwimTrainee(trainee); // Trying to add the same trainee again should throw an exception
    }

    @Test
    public void testRemoveTrainee_Successful() throws SwimTraineeAlreadyExistsException, SwimTraineeAgeException {
        swimAcademy.addSwimTrainee(trainee);
        assertTrue(swimAcademy.getSwimTrainees().contains(trainee));
        swimAcademy.removeSwimTrainee(trainee);
        assertFalse(swimAcademy.getSwimTrainees().contains(trainee));
    }

    @Test
    public void testAddTrainer_Successful() throws SwimTrainerAlreadyExistsException {
        SwimTrainer trainer = new SwimTrainer("Swim Trainer John");
        swimAcademy.addSwimTrainer(trainer);
        assertTrue(swimAcademy.getSwimTrainers().contains(trainer));
    }

    @Test(expected = SwimTrainerAlreadyExistsException.class)
    public void testAddTrainer_AlreadyExists() throws SwimTrainerAlreadyExistsException {
        SwimTrainer trainer = new SwimTrainer("Trainer Jane");
        swimAcademy.addSwimTrainer(trainer); // Adding the trainer once
        swimAcademy.addSwimTrainer(trainer); // Trying to add the same trainer again should throw an exception
    }

    @Test
    public void testRemovetrainer_Successful() throws SwimTrainerAlreadyExistsException {
        SwimTrainer trainer = new SwimTrainer("Swim Trainer Alice");
        swimAcademy.addSwimTrainer(trainer);
        assertTrue(swimAcademy.getSwimTrainers().contains(trainer));
        swimAcademy.removeSwimTrainer(trainer);
        assertFalse(swimAcademy.getSwimTrainers().contains(trainer));
    }

    @Test
    public void testAssignTrainerToSession_Successful() throws SwimmingSessionAlreadyHasSwimTrainerException {
        SwimTrainer trainer = new SwimTrainer("Swim Trainer John");
        SwimTrainingSession session = new SwimTrainingSession(1);
        swimAcademy.assignSwimTrainerToTrainingSession(trainer, session);
        assertEquals(trainer, session.getSwimTrainer());
    }

    @Test(expected = SwimmingSessionAlreadyHasSwimTrainerException.class)
    public void testAssignTrainerToSession_AlreadyHasTrainer() throws SwimmingSessionAlreadyHasSwimTrainerException {
        SwimTrainer trainer1 = new SwimTrainer("Swim Trainer Alice");
        SwimTrainer trainer2 = new SwimTrainer("Swim Trainer Bob");
        SwimTrainingSession session = new SwimTrainingSession(2);
        swimAcademy.assignSwimTrainerToTrainingSession(trainer1, session); // Assigning the first trainer
        swimAcademy.assignSwimTrainerToTrainingSession(trainer2, session); // Trying to assign another trainer should throw an exception
    }

    @Test
    public void testBookSession_Successful() throws NotEligibleForTrainingSessionException, TrainingSessionAlreadyBookedException, TrainingSessionIsFullException, SwimTraineeAlreadyExistsException {
        swimAcademy.addSwimTrainee(trainee);
        swimAcademy.addTrainingSession(session);
        swimAcademy.bookTrainingSession(trainee, session);
        assertTrue(trainee.isTrainingSessionBooked(session));
        assertTrue(session.getAttendees().contains(trainee));
    }

    @Test(expected = TrainingSessionAlreadyBookedException.class)
    public void testBookSession_AlreadyBooked() throws NotEligibleForTrainingSessionException, TrainingSessionAlreadyBookedException, TrainingSessionIsFullException, SwimTraineeAlreadyExistsException {
        swimAcademy.addSwimTrainee(trainee);
        swimAcademy.addTrainingSession(session);
        swimAcademy.bookTrainingSession(trainee, session);
        swimAcademy.bookTrainingSession(trainee, session);
    }

    @Test
    public void testCancelSession_Successful() throws NotEligibleForTrainingSessionException, TrainingSessionAlreadyBookedException, TrainingSessionIsFullException, TrainingSessionNotBookedException, TrainingSessionIsAttendedException, SwimTraineeAlreadyExistsException {
        swimAcademy.addSwimTrainee(trainee);
        swimAcademy.addTrainingSession(session);
        swimAcademy.bookTrainingSession(trainee, session);
        swimAcademy.cancelTrainingSession(trainee, session);
        assertFalse(trainee.isTrainingSessionBooked(session));
        assertFalse(session.getAttendees().contains(trainee));
    }

    @Test(expected = TrainingSessionNotBookedException.class)
    public void testCancelSession_NotBooked() throws TrainingSessionNotBookedException, TrainingSessionIsAttendedException {
        swimAcademy.cancelTrainingSession(trainee, session);
    }

    @Test
    public void testAttendSession_Successful() throws NotEligibleForTrainingSessionException, TrainingSessionAlreadyBookedException, TrainingSessionIsFullException, TrainingSessionNotBookedException, SwimTraineeAlreadyExistsException {
        swimAcademy.addSwimTrainee(trainee);
        swimAcademy.addTrainingSession(session);
        swimAcademy.bookTrainingSession(trainee, session);
        swimAcademy.attendTrainingSession(trainee, session);
        assertTrue(trainee.isTrainingSessionAttended(session));
    }

    @Test(expected = TrainingSessionNotBookedException.class)
    public void testAttendSession_NotBooked() throws TrainingSessionNotBookedException {
        swimAcademy.attendTrainingSession(trainee, session);
    }

    @Test
    public void testWriteEvaluation_Successful() throws NotEligibleForTrainingSessionException, RatingOutOfRangeException, WriteEvaluationException, SwimTraineeAlreadyExistsException, TrainingSessionIsFullException, TrainingSessionAlreadyBookedException, TrainingSessionNotBookedException {
        swimAcademy.addSwimTrainee(trainee);
        swimAcademy.addTrainingSession(session);
        swimAcademy.bookTrainingSession(trainee, session);
        swimAcademy.attendTrainingSession(trainee, session);

        int rating = 5;
        swimAcademy.writeEvaluation(trainee, session, rating);

        Evaluation evaluation = swimAcademy.getEvaluation().get(0);
        assertEquals(trainee, evaluation.getSwimTrainee());
        assertEquals(session, evaluation.getTrainingSession());
        assertEquals(rating, evaluation.getRating());
    }

    @Test(expected = NotEligibleForTrainingSessionException.class)
    public void testWriteEvaluation_NotEligibleForSession() throws NotEligibleForTrainingSessionException, RatingOutOfRangeException, WriteEvaluationException, SwimTraineeAlreadyExistsException, TrainingSessionIsFullException, TrainingSessionAlreadyBookedException {
        SwimTrainingSession session = new SwimTrainingSession(1234, 3);
        swimAcademy.addSwimTrainee(trainee);
        swimAcademy.addTrainingSession(session);
        swimAcademy.bookTrainingSession(trainee, session);
        swimAcademy.writeEvaluation(trainee, session, 5); // Trainee hasn't booked or attended the session
    }

    @Test(expected = WriteEvaluationException.class)
    public void testWriteEvaluation_NotAttendedSession() throws  RatingOutOfRangeException, WriteEvaluationException, SwimTraineeAlreadyExistsException, TrainingSessionIsFullException, TrainingSessionAlreadyBookedException {
        swimAcademy.addSwimTrainee(trainee);
        swimAcademy.addTrainingSession(session);
        swimAcademy.writeEvaluation(trainee, session, 5); // Trainee hasn't attended the session
    }

    @Test(expected = RatingOutOfRangeException.class)
    public void testWriteEvaluation_RatingOutOfRange() throws NotEligibleForTrainingSessionException, RatingOutOfRangeException, WriteEvaluationException, SwimTraineeAlreadyExistsException, TrainingSessionIsFullException, TrainingSessionAlreadyBookedException, TrainingSessionNotBookedException {
        swimAcademy.addSwimTrainee(trainee);
        swimAcademy.addTrainingSession(session);
        swimAcademy.bookTrainingSession(trainee, session);
        swimAcademy.attendTrainingSession(trainee, session);
        swimAcademy.writeEvaluation(trainee, session, 6); // Rating out of range
    }

    // Helper method to capture System.out
    private String captureOutput(Runnable code) {
        // Redirect System.out to a string
        var outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the code
        code.run();

        // Reset System.out
        System.setOut(System.out);

        // Return the captured output
        return outContent.toString();
    }
}