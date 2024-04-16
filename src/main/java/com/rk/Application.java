package com.rk;

import com.rk.entities.*;
import com.rk.exceptions.SwimTraineeAgeException;
import com.rk.exceptions.SwimTraineeAlreadyExistsException;
import com.rk.exceptions.SwimTrainerAlreadyExistsException;
import com.rk.service.SwimAcademy;

import java.util.Scanner;

public class Application {
    private static final SwimAcademy swimAcademy = new SwimAcademy();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            clearConsole();
            separator();
            System.out.println("Welcome to the Swim Academy Management System");
            separator();
            System.out.println("1. Add Swim Trainee");
            System.out.println("2. Remove Swim Trainee");
            System.out.println("3. Add Swim Trainer");
            System.out.println("4. Remove Swim Trainer");
            System.out.println("5. Add Training Session");
            System.out.println("6. Remove Training Session");
            System.out.println("7. View Swim Slots by Day");
            System.out.println("8. View Swim Slots by Grade");
            System.out.println("9. View Swim Slots by Trainer");
            System.out.println("10. Book Training Session");
            System.out.println("11. Cancel Training Session");
            System.out.println("12. Attend Training Session");
            System.out.println("13. Write Evaluation");
            System.out.println("14. Generate Swim Trainees Report");
            System.out.println("15. Generate Swim Trainers Report");
            System.out.println("0. Exit");
            separator();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    clearConsole();
                    separator();
                    System.out.println("Add Swim Trainee");
                    separator();
                    addSwimTrainee();
                    break;
                case 2:
                    clearConsole();
                    separator();
                    System.out.println("Remove Swim Trainee");
                    separator();
                    removeSwimTrainee();
                    break;
                case 3:
                    clearConsole();
                    separator();
                    System.out.println("Add Swim Trainer");
                    separator();
                    addSwimTrainer();
                    break;
                case 4:
                    clearConsole();
                    separator();
                    System.out.println("Remove Swim Trainer");
                    separator();
                    removeSwimTrainer();
                    break;
                case 5:
                    clearConsole();
                    separator();
                    System.out.println("Add Training Session");
                    separator();
                    addTrainingSession();
                    break;
                case 6:
                    clearConsole();
                    separator();
                    System.out.println("Remove Training Session");
                    separator();
                    removeTrainingSession();
                    break;
                case 7:
                    clearConsole();
                    separator();
                    System.out.println("Swim Slots by Day");
                    separator();
                    viewSwimSlotByDay();
                    break;
                case 8:
                    clearConsole();
                    separator();
                    System.out.println("Swim Slots by Grade");
                    separator();
                    viewSwimSlotsByGrade();
                    break;
                case 9:
                    clearConsole();
                    separator();
                    System.out.println("Swim Slots by Trainer");
                    separator();
                    viewSwimSlotsByTrainer();
                    break;
                case 10:
                    clearConsole();
                    separator();
                    System.out.println("Book Training Session");
                    separator();
                    bookTrainingSession();
                    break;
                case 11:
                    clearConsole();
                    separator();
                    System.out.println("Cancel Training Session");
                    separator();
                    cancelTrainingSession();
                    break;
                case 12:
                    clearConsole();
                    separator();
                    System.out.println("Attend Training Session");
                    separator();
                    attendTrainingSession();
                    break;
                case 13:
                    clearConsole();
                    separator();
                    System.out.println("Write Evaluation");
                    separator();
                    writeEvaluation();
                    break;
                case 14:
                    swimAcademy.generateSwimTraineeReport();
                    break;
                case 15:
                    swimAcademy.generateSwimTrainerReport();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    scanner.nextLine();
            }
        }
    }

    private static void addSwimTrainee() {
        System.out.print("Enter swim trainee name: ");
        String traineeName = scanner.nextLine();
        System.out.print("Enter swim trainee gender: ");
        String traineeGender = scanner.nextLine();
        System.out.print("Enter swim trainee age: ");
        int traineeAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter swim trainee emergency contact: ");
        String traineeEmergencyContact = scanner.nextLine();
        System.out.print("Enter swim trainee grade: ");
        int traineeGrade = scanner.nextInt();
        scanner.nextLine();
        try {
            swimAcademy.addSwimTrainee(
                    new SwimTrainee(
                            traineeName,
                            traineeGender,
                            traineeAge,
                            traineeEmergencyContact,
                            traineeGrade
                    )
            );
            System.out.println("Swim Trainee added successfully!");
            scanner.nextLine();
        } catch (SwimTraineeAlreadyExistsException | SwimTraineeAgeException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    private static void removeSwimTrainee() {
        System.out.print("Enter swim trainee name: ");
        SwimTrainee trainee = swimAcademy.getSwimTrainees().stream().filter(
                l -> l.getName().equals(scanner.nextLine())
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Swim Trainee not found!");
            scanner.nextLine();
            return;
        }
        swimAcademy.removeSwimTrainee(trainee);
        System.out.println("Swim Trainee removed successfully!");
        scanner.nextLine();
    }

    private static void addSwimTrainer() {
        System.out.print("Enter swim trainer name: ");
        String trainerName = scanner.nextLine();
        try {
            swimAcademy.addSwimTrainer(
                    new SwimTrainer(
                            trainerName
                    )
            );
            System.out.println("Swim Trainer added successfully!");
            scanner.nextLine();
        } catch (SwimTrainerAlreadyExistsException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    private static void removeSwimTrainer() {
        System.out.print("Enter swim trainer name: ");
        SwimTrainer trainer = swimAcademy.getSwimTrainers().stream().filter(
                c -> c.getName().equals(scanner.nextLine())
        ).findFirst().orElse(null);
        if (trainer == null) {
            System.out.println("Swim Trainer not found!");
            scanner.nextLine();
            return;
        }
        swimAcademy.removeSwimTrainer(trainer);
        System.out.println("Swim Trainer removed successfully!");
        scanner.nextLine();
    }

    private static void addTrainingSession() {
        System.out.print("Days");
        System.out.println("1. "+ TrainingDay.MONDAY);
        System.out.println("2. "+ TrainingDay.WEDNESDAY);
        System.out.println("3. "+ TrainingDay.FRIDAY);
        System.out.println("4. "+ TrainingDay.SATURDAY);
        System.out.println("0. Exit");
        TrainingDay day = null;
        System.out.print("Enter day: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                day = TrainingDay.MONDAY;
                break;
            case 2:
                day = TrainingDay.WEDNESDAY;
                break;
            case 3:
                day = TrainingDay.FRIDAY;
                break;
            case 4:
                day = TrainingDay.SATURDAY;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        System.out.print("Times");
        System.out.println("1. "+ SwimSlot.From2To3);
        System.out.println("2. "+ SwimSlot.From3To4);
        System.out.println("3. "+ SwimSlot.From4To5);
        System.out.println("4. "+ SwimSlot.From5To6);
        System.out.println("5. "+ SwimSlot.From6To7);
        System.out.println("0. Exit");
        SwimSlot time = null;
        System.out.print("Enter time: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                time = SwimSlot.From2To3;
                break;
            case 2:
                time = SwimSlot.From3To4;
                break;
            case 3:
                time = SwimSlot.From4To5;
                break;
            case 4:
                time = SwimSlot.From5To6;
                break;
            case 5:
                time = SwimSlot.From6To7;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter trainer name: ");
        String trainerName = scanner.nextLine();
        SwimTrainer trainer = swimAcademy.getSwimTrainers().stream().filter(
                c -> c.getName().equals(trainerName)
        ).findFirst().orElse(null);
        if (trainer == null) {
            System.out.println("Swim Trainer not found!");
            scanner.nextLine();
            return;
        }
        //scanner.nextLine();
        swimAcademy.addTrainingSession(
                new SwimTrainingSession(
                        (int) (Math.random() * 1000),
                        time,
                        trainer,
                        day,
                        grade
                )
        );
        System.out.println("Training Session added successfully!");
        scanner.nextLine();
    }

    private static void removeTrainingSession() {
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimTrainingSession session = swimAcademy.getTrainingSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Training Session not found!");
            scanner.nextLine();
            return;
        }
        swimAcademy.removeTrainingSession(
                session
        );
        System.out.println("Training Session removed successfully!");
        scanner.nextLine();
    }

    private static void viewSwimSlotByDay() {
        System.out.print("Days");
        System.out.println("1. "+ TrainingDay.MONDAY);
        System.out.println("2. "+ TrainingDay.WEDNESDAY);
        System.out.println("3. "+ TrainingDay.FRIDAY);
        System.out.println("4. "+ TrainingDay.SATURDAY);
        System.out.println("0. Exit");
        TrainingDay day = null;
        System.out.print("Enter day: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                day = TrainingDay.MONDAY;
                break;
            case 2:
                day = TrainingDay.WEDNESDAY;
                break;
            case 3:
                day = TrainingDay.FRIDAY;
                break;
            case 4:
                day = TrainingDay.SATURDAY;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        swimAcademy.viewSwimSlotsByDay(day);
        scanner.nextLine();
    }

    private static void viewSwimSlotsByGrade() {
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        swimAcademy.viewSwimSlotsByGrade(grade);
        scanner.nextLine();
    }

    private static void viewSwimSlotsByTrainer() {
        System.out.print("Enter swim trainer name: ");
        String trainerName = scanner.nextLine();
        SwimTrainer trainer = swimAcademy.getSwimTrainers().stream().filter(
                c -> c.getName().equals(trainerName)
        ).findFirst().orElse(null);

        if (trainer == null) {
            System.out.println("Swim Trainer not found!");
            scanner.nextLine();
            return;
        }

        swimAcademy.viewSwimSlotsBySwimTrainer(
                trainer
        );
        scanner.nextLine();
    }

    private static void bookTrainingSession() {
        System.out.print("Enter swim trainee name: ");
        String traineeName = scanner.nextLine();
        SwimTrainee trainee = swimAcademy.getSwimTrainees().stream().filter(
                l -> l.getName().equals(traineeName)
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Swim Trainee not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter training session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimTrainingSession session = swimAcademy.getTrainingSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Training Session not found!");
            scanner.nextLine();
            return;
        }
        try {
            swimAcademy.bookTrainingSession(
                    trainee,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Session booked successfully!");
        scanner.nextLine();
    }

    private static void cancelTrainingSession() {
        System.out.print("Enter trainee name: ");
        String traineeName = scanner.nextLine();
        SwimTrainee trainee = swimAcademy.getSwimTrainees().stream().filter(
                l -> l.getName().equals(traineeName)
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Swim Trainee not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter training session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimTrainingSession session = swimAcademy.getTrainingSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        try {
            swimAcademy.cancelTrainingSession(
                    trainee,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Training Session cancelled successfully!");
        scanner.nextLine();
    }

    private static void attendTrainingSession() {
        System.out.print("Enter swim trainee name: ");
        String traineeName = scanner.nextLine();
        SwimTrainee trainee = swimAcademy.getSwimTrainees().stream().filter(
                l -> l.getName().equals(traineeName)
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Swim Trainee not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimTrainingSession session = swimAcademy.getTrainingSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Training Session not found!");
            return;
        }
        try {
            swimAcademy.attendTrainingSession(
                    trainee,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Training Session attended successfully!");
        scanner.nextLine();
    }

    private static void writeEvaluation() {
        System.out.print("Enter swim trainee name: ");
        String traineeName = scanner.nextLine();
        SwimTrainee trainee = swimAcademy.getSwimTrainees().stream().filter(
                l -> l.getName().equals(traineeName)
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Swim Trainee not found!");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter Training Session ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimTrainingSession session = swimAcademy.getTrainingSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Training Session not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter Evaluation: 1-5 ");
        int review = scanner.nextInt();
        scanner.nextLine();
        try {
            swimAcademy.writeEvaluation(
                    trainee,
                            session,
                            review
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Evaluation written successfully!");
        scanner.nextLine();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static void separator() {
        System.out.println("------------------------------------------------");
    }
}
