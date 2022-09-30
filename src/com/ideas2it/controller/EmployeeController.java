package com.ideas2it.controller;

import java.lang.System;
import java.time.format.DateTimeFormatter; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.ideas2it.model.Skills;
import com.ideas2it.model.Trainer;
import com.ideas2it.model.Trainee;
import com.ideas2it.service.EmployeeService;

/**
 * EmployeeController class has the control to display
 * Trainer & Trainee Details using model, service & dao
 *
 * @version 1.0 29 Aug 2022
 *
 * @author  Ramasamy R M
 */
public class EmployeeController { 

    private EmployeeService employeeService = new EmployeeService();

    private Scanner scanner = new Scanner(System.in);

    /**
     * <p>
     * Initial level of the application
     * </p>
     *
     * @param
     * @return void
     */
    public void initiate() {

        Boolean choice = true;
        Integer option; 
        try {
            while (choice) {
                System.out.println("\n\t\t\t\tWelcome to Ideas2IT!"
                        + "\n\t\t\t\t--------------------");
                System.out.println("\t\t\tPlease Select your option from the list");
                System.out.println("\n\tTrainer\t\t\t\tTrainee\n"
                        + "\t-------\t\t\t\t-------\n"
                        + "1. Create New Trainer\t\t| 6. Create New Trainee\t\t | "
                        + "11. Search Employee\n2. Display All Trainer Details\t| "
                        + "7. Display All Trainee Details | 12. Exit\n"
                        + "3. Display Trainer Based on ID\t| 8. Display Trainee Based on ID |\n"
                        + "4. Update Trainer Based on ID\t| 9. Update Trainee Based on ID\t |\n"
                        + "5. Delete Trainer Based on ID\t| 10. Delete Trainee Based on ID |\n");
                System.out.print("Enter your option : ");
                scanner = new Scanner(System.in);
                option = scanner.nextInt();
                listOfOptions(option);
            }
        } catch (Exception exception) {
            System.out.println("Enter Correct Number Format");
            exception.getMessage();
            initiate();
        }
    }

    /**
     * <p>
     * Contains the create, displayAll, displayById, updateById, deleteById
     * method for both Trainer & Trainee.
     * </p>
     *
     * @param int option
     * @return void
     */
    public void listOfOptions(int option) {
        switch(option) {
        case 1, 6:
            createNewEmployee(option);
        break;
        case 2:
            displayAllTrainers();
            break;
        case 3:
            System.out.print("Enter the Employee Id: ");
            Integer id = scanner.nextInt();
            displayTrainerById(id);
            break;
        case 4:
            System.out.print("Enter the Employee Id: ");
            id = scanner.nextInt();
            updateTrainerById(id);
            break;
        case 5: 
            System.out.print("Enter the Employee Id: ");
            id = scanner.nextInt();
            deleteTrainerById(id);
            break;
        case 7:
            displayAllTrainees();
            break;		
        case 8:
            System.out.print("Enter the Employee Id: ");
            id = scanner.nextInt();
            displayTraineeById(id);
            break;
        case 9: 
            System.out.print("Enter the Employee Id: ");
            id = scanner.nextInt();
            updateTraineeById(id);
            break;
        case 10:
            System.out.print("Enter the Employee Id: ");
            id = scanner.nextInt();
            deleteTraineeById(id);
            break;
        case 11:
            searchAnEmployee();
            break;
        case 12:
            System.out.println("Thank You!!!");
            System.exit(0);
            break;
        default:
            System.out.println("PLEASE ENTER CORRECT OPTION!!!");
            break;
        }
    }

    /**
     * <p>
     * Create New Employee by collecting their name, designation, department,
     * phone number. Here, we generate Employee Id by providing some statements.
     * For both Trainer & Trainee, these are common attributes.
     * </p>
     *
     * @param int option
     * @return void
     */
    public void createNewEmployee(int option) {

        System.out.println("\nCreate New Employee");
        System.out.println("--------------------");

        Integer employeeId = 0;

        String firstName;
        Boolean isValidFirstName;
        do {
            System.out.print("First Name        : ");
            firstName = scanner.next();
            isValidFirstName = employeeService.isValidFirstName(firstName);
            if (!isValidFirstName) { 
                System.out.println("Entered First Name is Invalid");
            }
        } while (!isValidFirstName);

        String lastName;
        Boolean isValidLastName;
        do {
            System.out.print("Last Name         : ");
            lastName = scanner.next();
            isValidLastName = employeeService.isValidLastName(lastName);
            if (!isValidLastName) {
                System.out.println("Entered Last Name is Invalid");
            }
        } while (!isValidLastName);

        System.out.print("Designation       : ");
        String designation = scanner.next();

        System.out.print("Department        : ");
        String department = scanner.next();

        String phoneNumber;
        Boolean isValidPhoneNumber;
        do {
            System.out.print("Phone Number      : ");
            phoneNumber = scanner.next();
            isValidPhoneNumber = employeeService.isValidPhoneNumber(phoneNumber);
            if (!isValidPhoneNumber) {
                System.out.println("Entered Phone number is invalid.");
            }
        } while (!isValidPhoneNumber);

        String emailId;
        Boolean isValidEmailId;
        do {
            System.out.print("Email ID : ");
            emailId = scanner.next();
            isValidEmailId = employeeService.isValidEmailId(emailId);
            if (!isValidEmailId) {
                System.out.println("Enter Correct Email Id");
            }
        } while (!isValidEmailId);

        String dateOfBirth;
        Boolean isValidAge;
        do {
            System.out.print("Date of birth in DD/MM/YYYY : ");
            dateOfBirth = scanner.next();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
            LocalDate birthDate = LocalDate.parse(dateOfBirth, dateFormat);
            isValidAge = employeeService.isValidEmployee(birthDate);
            if (!isValidAge) {
                System.out.println("Not Eligibile to be an Employee!!!");
                System.exit(0);
            }
        } while (!isValidAge);

        System.out.print("Previous Experience in yrs : ");
        String previousExperience = scanner.next();

        String dateOfJoining;
        Boolean isValidJoining;
        do {
            System.out.print("Date of Joining in DD/MM/YYYY : ");
            dateOfJoining = scanner.next();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            LocalDate joiningDate = LocalDate.parse(dateOfJoining, dateFormat);
            isValidJoining = employeeService.isFutureDate(joiningDate);
            if (!isValidJoining) {
                System.out.println("Date of joining must not be a future Date");
            }
        } while (!isValidJoining);

        if (option == 1) {
            createNewTrainer(employeeId, firstName, lastName, designation, department, 
                    Long.parseLong(phoneNumber), emailId, dateOfBirth, 
                    Float.parseFloat(previousExperience), dateOfJoining);
        } else {
            createNewTrainee(employeeId, firstName, lastName, designation, department, 
                    Long.parseLong(phoneNumber), emailId, dateOfBirth, 
                    Float.parseFloat(previousExperience), dateOfJoining);
        }
    }

    /**
     * <p>
     * Create New Trainer by collecting details from employee method and also collecting
     * their salary and we store the details in employeeService.
     * </p>
     *
     * @param Integer id, String firstName, String lastName, String designation,
     *        String department, long phoneNumber, String emailId, String dateOfBirth, 
     *        float previousExperience, String dateOfJoining
     * @return void
     */
    public void createNewTrainer(Integer id, String firstName, String lastName, String designation,
            String department, Long phoneNumber, String emailId,  
            String dateOfBirth, Float previousExperience, 
            String dateOfJoining) { 

        System.out.print("Salary            : ");
        Long salary = scanner.nextLong();  

        Integer employeeId = employeeService.addTrainer(id, firstName, lastName, designation, department,
                phoneNumber, emailId, dateOfBirth, 
                previousExperience, dateOfJoining, salary);
        System.out.println("Employee Id : " + employeeId);
    }

    /**
     * <p>
     * Display All Trainers details which contains Id, name, designation, department,
     * phone number, date of birth, previous experience, date of joining, salary. 
     * </p>
     *
     * @param 
     * @return void
     */
    public void displayAllTrainers() {
        List<Trainer> trainers = employeeService.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No records!");
        } else {
            Integer iterate = 1;
            for (Trainer trainer : trainers) {
                System.out.println("\nTrainer Detail" + " (" + iterate + "):");
                System.out.println("-------------------");
                System.out.println(trainer);
                iterate++;
            }
        }
    }

    /**
     * <p>
     * Display specific Trainer by getting Id from the user. Which displays Id, 
     * name, designation, department,phone number, salary & experience. 
     * </p>
     *
     * @param Integer employeeId
     * @return 
     */
    public void displayTrainerById(Integer employeeId) {
        List<Trainer> trainers = employeeService.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No records!");
        } else {
            Boolean checkById = employeeService.checkTrainerById(employeeId); 
            if (checkById == true) {
                Trainer trainer = employeeService.getTrainerById(employeeId);
                System.out.println(trainer);
            } else {
                System.out.println("There is no existing record for the given ID!");
            }
        }
    } 

    /**
     * <p>
     * Update specific Trainer by getting Id from the user. Which updates 
     * name, designation, department, phone number, salary & experience for the particular id. 
     * </p>
     *
     * @param Integer employeeId
     * @return void
     */
    public void updateTrainerById(Integer employeeId) {
        int updateOption = 0;
        List<Trainer> trainers = employeeService.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No records!");
        } else {
            Boolean checkById = employeeService.checkTrainerById(employeeId);
            if (checkById == true) {
                do {
                    displayTrainerById(employeeId);
                    System.out.println("Press 1. Confirm Update All Details\t2. Back");
                    updateOption = scanner.nextInt();
                    switch (updateOption) {
                    case 1:
                        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                        System.out.print("First Name        : ");
                        String firstName = scanner.nextLine();
                        System.out.print("Last Name         : ");
                        String lastName = scanner.nextLine();
                        System.out.print("Designation           : ");
                        String designation = scanner.nextLine();
                        System.out.print("Department            : ");
                        String department = scanner.nextLine();
                        System.out.print("Phone Number      : ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Email ID : ");
                        String emailId = scanner.nextLine();
                        System.out.print("Date of Birth(DD/MM/YYYY): ");
                        String dateOfBirth = scanner.nextLine();
                        System.out.print("Previous Experience in yrs : ");
                        String previousExperience = scanner.nextLine();
                        System.out.print("Date of Join (DD/MM/YYYY): ");
                        String dateOfJoining = scanner.nextLine();
                        System.out.print("Salary            : ");
                        String salary = scanner.nextLine();

                        employeeService.updateTrainerById(employeeId, firstName, lastName, 
                                designation, department, phoneNumber, emailId, dateOfBirth,
                                previousExperience, dateOfJoining, salary);
                        System.out.println("Record Updated Successfully!");
                        break;
                    case 2: 
                        System.out.println("No Record has been Updated!");
                        break;
                    default:
                        System.out.println("Choose correct option!!!");
                        break;
                    }
                } while (updateOption != 2);
            } else {
                System.out.println("There is no existing record for the given ID!");
            }
        }
    }

    /**
     * <p>
     * Delete specific Trainer by getting Id from the user. Which deletes 
     * all the details for the particular id.  
     * </p>
     *
     * @param Integer employeeId - employee id value for the Trainer delete operation
     * @return void
     */
    public void deleteTrainerById(Integer employeeId) {
        Integer updateOption = 0;
        List<Trainer> trainers = employeeService.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No records!");
        } else {
            Boolean checkById = employeeService.checkTrainerById(employeeId);
            if (checkById == true) {
                displayTrainerById(employeeId);
                System.out.println("1. Confirm Delete Details\t2. Back");
                updateOption = scanner.nextInt();
                switch (updateOption) {
                case 1:
                    employeeService.deleteTrainerById(employeeId);
                    System.out.println("Employee ID : " + employeeId + " Deleted Successfully!");
                    break;
                case 2:
                    System.out.println("No Record has been Deleted!");
                    break;
                default:
                    System.out.println("Choose correct option!!!");
                    break;
                }
            } else {
                System.out.println("There is no existing record for the given ID!");
            }
        }
    }

    /**
     * <p>
     * Create New Trainee by collecting details from employee method and also collecting
     * their passed out year & list of skills and we store the details in employeeService.
     * </p>
     *
     * @param Integer id, String firstName, String lastName, String designation,
     *        String department, long phoneNumber, String emailId, String dateOfBirth, 
     *        float previousExperience, String dateOfJoining
     * @return void
     */
    public void createNewTrainee(Integer id, String firstName, String lastName, 
            String designation, String department, Long phoneNumber,   
            String emailId, String dateOfBirth, Float previousExperience,
            String dateOfJoining) { 
        System.out.print("Passed Out Year     : ");
        Integer passedOutYear = scanner.nextInt();
        Set<Skills> skillSet = new LinkedHashSet<Skills>();
        System.out.println("Known Skills      : ");
        System.out.print("Enter Total no. of Skills : ");
        int numberOfSkills = scanner.nextInt();
        for (int listIndex = 1; listIndex <= numberOfSkills; listIndex++) {
            Skills skills = new Skills();
            System.out.println("Enter the Skill " + listIndex + " : ");
            String skillName = scanner.next();
            skills.setSkillName(skillName);
            System.out.print("Enter Version : ");
            String skillVersion =  scanner.next();
            skills.setSkillVersion(skillVersion);
            System.out.print("Enter Last Used Year : ");
            Integer lastUsedYear = scanner.nextInt();
            skills.setLastUsedYear(lastUsedYear);
            System.out.print("Enter Skill Experience : ");
            Float skillExperience = scanner.nextFloat();
            skills.setSkillExperience(skillExperience);
            skillSet.add(skills);
        }
        System.out.println("\nTrainee created Successfully!!!");
        Integer employeeId = employeeService.addTrainee(id, firstName, lastName, designation, 
                department, phoneNumber, emailId, dateOfBirth, previousExperience, dateOfJoining,
                passedOutYear, skillSet);
        System.out.println("Employee Id : " + employeeId);
    }

    /**
     * <p>
     * Display All Trainees details which contains Id, name, designation, department,
     * phone number, pass out year & skill. 
     * </p>
     *
     * @param 
     * @return void
     */    
    public void displayAllTrainees() {
        List<Trainee> trainees = employeeService.getAllTrainees();
        if (trainees.isEmpty()) {
            System.out.println("No records!");
        } else {
            Integer iterate = 1;
            for (Trainee trainee : trainees) {
                System.out.println("\nTrainee Detail" + " (" + iterate + "):");
                System.out.println("-------------------");
                System.out.println(trainee);
                iterate++;
            }
        }
    }

    /**
     * <p>
     * Display specific Trainee by getting Id from the user, which displays Id, 
     * name, designation, department, phone number, date of birth, previous experience,
     * date of joining, passed out year & skill. 
     * </p>
     *
     * @param Integer employeeId
     * @return void
     */
    public void displayTraineeById(Integer employeeId) {
        List<Trainee> trainees = employeeService.getAllTrainees();
        if (trainees.isEmpty()){
            System.out.println("No records!");
        } else {
            Boolean checkById = employeeService.checkTraineeById(employeeId);
            if (checkById == true) {
                Trainee trainee = employeeService.getTraineeById(employeeId); 
                System.out.println(trainee);
            } else {
                System.out.println("There is no existing record for the given ID!");
            }
        }
    }    

    /**
     * <p>
     * Update specific Trainee by getting Id from the user, which updates 
     * name, designation, department, phone number, date of birth, previous experience,
     * date of joining, pass out year & skill for the particular id. 
     * </p>
     *
     * @param Integer employeeId
     * @return void
     */
    public void updateTraineeById(Integer employeeId) {
        Integer updateOption = 0;
        List<Trainee> trainees = employeeService.getAllTrainees();
        if (trainees.isEmpty()) {
            System.out.println("No records!");
        } else {
            Boolean checkById = employeeService.checkTraineeById(employeeId);
            if (checkById == true) {
                do {
                    displayTraineeById(employeeId);
                    System.out.println("1. Confirm Update All Details\t2. Back");
                    updateOption = scanner.nextInt();
                    switch (updateOption) {
                    case 1:
                        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                        System.out.print("First Name        : ");
                        String firstName = scanner.nextLine();
                        System.out.print("Last Name         : ");
                        String lastName = scanner.nextLine();
                        System.out.print("Designation           : ");
                        String designation = scanner.nextLine();
                        System.out.print("Department            : ");
                        String department = scanner.nextLine();
                        System.out.print("Phone Number      : ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Email ID : ");
                        String emailId = scanner.nextLine();
                        System.out.print("Date of Birth(DD/MM/YYYY): ");
                        String dateOfBirth = scanner.nextLine();
                        System.out.println("Previous Experience : ");
                        String previousExperience = scanner.nextLine();
                        System.out.print("Date of Join (DD/MM/YYYY): ");
                        String dateOfJoining = scanner.nextLine();
                        System.out.print("Passed Out Year     : ");
                        String passedOutYear = scanner.nextLine();
                        employeeService.updateTraineeById(employeeId, firstName, lastName, 
                                designation, department, phoneNumber, emailId, dateOfBirth, 
                                previousExperience, dateOfJoining, passedOutYear);
                        System.out.println("Record has been Updated Successfully!");
                        break;
                    case 2:
                        System.out.println("No Record has bee Updated!");
                        break;
                    default:
                        System.out.println("Choose correct option!!!");
                        break;
                    }
                } while (updateOption != 2);
            } else {
                System.out.println("There is no existing record for the given ID!");
            } 
        }
    }

    /**
     * <p>
     * Delete specific Trainee by getting Id from the user. Which deletes 
     * all the details for the particular id. 
     * </p>
     *
     * @param Integer employeeId
     * @return void
     */
    public void deleteTraineeById(Integer employeeId) {
        Integer updateOption = 0;
        List<Trainee> trainees = employeeService.getAllTrainees();
        if (trainees.isEmpty()){
            System.out.println("No records!");
        } else {
            Boolean checkById = employeeService.checkTraineeById(employeeId);
            if (checkById == true) {
                displayTraineeById(employeeId);
                System.out.println("1. Confirm Delete Details\t2. Back");
                updateOption = scanner.nextInt();
                switch (updateOption) {
                case 1:
                    employeeService.deleteTraineeById(employeeId);
                    System.out.println("Employee ID : " + employeeId + " Deleted Successfully!");
                    break;
                case 2:
                    System.out.println("No Record has been Deleted!");
                    break;
                default:
                    System.out.println("Choose correct option!!!");
                    break;
                }
            } else {
                System.out.println("There is no existing record for the given ID!");
            }
        }
    }

    /**
     * <p>
     * Search All Employee details - both Trainer & Trainee 
     * based on Firstname & Lastname of an Employee. 
     * </p>
     *
     * @param 
     * @return void
     */
    public void searchAnEmployee() {
        List<Trainer> trainers = new ArrayList<Trainer>();
        List<Trainee> trainees = new ArrayList<Trainee>();
        System.out.println("Search an Employee based on following ways");
        System.out.println("1. First Name\t2. Last Name");
        System.out.print("Enter your way : ");
        Integer searchWay = scanner.nextInt();
        switch (searchWay) {
        case 1: 
            System.out.println("Searching based on First Name");
            System.out.print("Enter Employee First Name : ");
            String firstName = scanner.next();
            trainers = employeeService.searchTrainerByFirstName(firstName);
            trainees = employeeService.searchTraineeByFirstName(firstName);
            if (trainers != null) {
                System.out.println(trainers); 
            }
            if (trainees != null) {
                System.out.println(trainees);
            }
            break;
        case 2: 
            System.out.println("Searching based on Last Name");
            System.out.print("Enter Employee Last Name : ");
            String lastName = scanner.next();
            trainers = employeeService.searchTrainerByLastName(lastName); 
            trainees = employeeService.searchTraineeByLastName(lastName);
            if (trainers != null) {
                System.out.println(trainers); 
            }
            if (trainees != null) {
                System.out.println(trainees);
            }
            break;
        default:
            System.out.println("Enter Correct Option!");
            break;
        }
    }
}
