package com.ideas2it.controller;

import java.lang.System;
//import java.sql.Date;
import java.time.format.DateTimeFormatter; 
import java.time.LocalDate;
//import java.time.Period;
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
	 * <p>
	 * Ex: First Name : xyz, Last Name : abc, Designation : lead, Department : technical,
	 *     phone number : 9876543210, Date of birth : 10/10/1980, 
	 *     Previous Experience in yrs : 0.3, Date of Joining : 18/12/2010
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
			isValidFirstName = employeeService.validateFirstName(firstName);
			if (!isValidFirstName) { 
				System.out.println("Entered First Name is Invalid");
			}
		} while (!isValidFirstName);

		String lastName;
		Boolean isValidLastName;
		do {
			System.out.print("Last Name         : ");
			lastName = scanner.next();
			isValidLastName = employeeService.validateLastName(lastName);
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
			isValidPhoneNumber = employeeService.validatePhoneNumber(phoneNumber);
			if (!isValidPhoneNumber) {
				System.out.println("Entered Phone number is invalid.");
			}
		} while (!isValidPhoneNumber);

		String emailId;
		Boolean isValidEmailId;
		do {
			System.out.print("Email ID : ");
			emailId = scanner.next();
			isValidEmailId = employeeService.validateEmailId(emailId);
			if (!isValidEmailId) {
				System.out.println("Enter Correct Email Id");
			}
		} while (!isValidEmailId);

		System.out.print("Date of birth in DD/MM/YYYY : ");
		String dateOfBirth = scanner.next();
		// DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		//LocalDate birthDate = LocalDate.parse(dateOfBirth, dateFormat);

		System.out.print("Previous Experience in yrs : ");
		String previousExperience = scanner.next();

		System.out.print("Date of Joining in DD/MM/YYYY : ");
		String dateOfJoining = scanner.next();
		//LocalDate joiningDate = LocalDate.parse(dateOfJoining, dateFormat);

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
	 * <p>
	 * Ex: First Name : xyz, Last Name : abc, Designation : lead, Department : technical,
	 *     phone number : 9876543210, Date of birth : 10/10/1980, 
	 *     Previous Experience in yrs : 0.3, Date of Join : 18/12/2010, Salary : 21000
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

		System.out.println(employeeService.addTrainer(id, firstName, lastName, designation, department,
				phoneNumber, emailId, dateOfBirth, 
				previousExperience, dateOfJoining, salary));
	}

	/**
	 * <p>
	 * Display All Trainers details which contains Id, name, designation, department,
	 * phone number, date of birth, previous experience, date of joining, salary. 
	 * </p>
	 * <p>
	 * Ex: Id : I22001, Name : xyz, Designation : lead, Department : technical,
	 *     phone number : 9876543210, Date of birth : 10/10/1980, 
	 *     Previous Experience in yrs : 0.3, Date of Join : 18/12/2010, Salary : 40000
	 * Ex: Id : I22002, Name : uvx, Designation : trainer, Department : development,
	 *     phone number : 8901234567, Date of birth : 10/10/1980, 
	 *     Previous Experience in yrs : 0.3, Date of Join : 18/12/2010, Salary : 35000
	 * Ex: If we give Employee Id : I2003, it checks the employee id,
	 *     if the employee id is not match, it shows "No Record".
	 * </p>
	 *
	 * @param 
	 * @return void
	 */
	public void displayAllTrainers() {
		if (employeeService.getAllTrainers().isEmpty()) {
			System.out.println("No records!");
		} else {
			List<Trainer> trainers = employeeService.getAllTrainers();
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
	 * <p>
	 * Ex: If we give Employee Id : 1, it will display all the trainer details
	 *         Id : 1, Name : xyz, Designation : lead, Department : technical,
	 *         phone number : 9876543210, Date of birth : 10/10/1980, 
	 *         Previous Experience in yrs : 0.3, Date of Join : 18/12/2010, Salary : 40000
	 * Ex: If we give Employee Id : 3, it checks the employee id,
	 *     if the employee id is not match, it shows "No Record".
	 * </p>
	 *
	 * @param Integer employeeId
	 * @return 
	 */
	public void displayTrainerById(Integer employeeId) {
		if (employeeService.getAllTrainers().isEmpty()) {
			System.out.println("No records!");
		} else {
			if (employeeService.checkTrainerById(employeeId)) {
				Trainer trainer = employeeService.getTrainerById(employeeId);
				//for (Trainer trainer : trainers) {
				System.out.println(trainer);
				//}
			} else {
				System.out.println("There is no existing record for the given ID!");
			}
		}
	} 

	/**
	 * <p>
	 * Update specific Trainer by getting Id from the user. Which updates 
	 * name, designation, department,phone number, salary & experience for the particular id. 
	 * </p>
	 * <p>
	 * Ex: If we give Employee Id : 1, it will ask all the trainer details to update
	 *         Id : 1, Name : xyz, Designation : lead, Department : technical,
	 *         phone number : 9876543210, Date of birth : 10/10/1980,
	 *         Previous Experience in yrs : 0.3, Date of Join : 18/12/2010, Salary : 40000
	 * Ex: If we give Employee Id : 3, it checks the employee id,
	 *     if the employee id is not match, it shows "No Record".
	 * </p>
	 *
	 * @param Integer employeeId
	 * @return void
	 */
	public void updateTrainerById(Integer employeeId) {
		int updateOption = 0;
		if (employeeService.getAllTrainers().isEmpty()) {
			System.out.println("No records!");
		} else {
			if (employeeService.checkTrainerById(employeeId)) {
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
	 * <p>
	 * Ex: If we give Employee Id : 1, it will delete all the trainer details in that id,
	 *         and it shows "Deleted"
	 * Ex: If we give Employee Id : 3, it checks the employee id,
	 *     if the employee id is not match, it shows "No Record".
	 * </p>
	 *
	 * @param Integer employeeId - employee id value for the Trainer delete operation
	 * @return void
	 */
	public void deleteTrainerById(Integer employeeId) {
		Integer updateOption = 0;
		if (employeeService.getAllTrainers().isEmpty()) {
			System.out.println("No records!");
		} else {
			if (employeeService.checkTrainerById(employeeId)) {
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
	 * <p>
	 * Ex: Id : 4, Name : xyz, Designation : set, Department : cse,
	 *     Phone number : 9876543210, Date of birth : 10/10/1980, 
	 *     Previous Experience in yrs : 0.3, Date of Joining : 18/12/2010, 
	 *     Passed out year : 2021, Skill : Java
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
		System.out.println(employeeService.addTrainee(id, firstName, lastName, designation, department,  
				phoneNumber, emailId, dateOfBirth, previousExperience,
				dateOfJoining, passedOutYear, skillSet));
	}

	/**
	 * <p>
	 * Display All Trainees details which contains Id, name, designation, department,
	 * phone number, pass out year & skill. 
	 * </p>
	 * <p>
	 * Ex: Id : 5, Name : xyz, Designation : set, Department : cse,
	 *     phone number : 9876543210, Date of birth : 10/10/1980, Previous Experience in yrs : 0.3,
	 *     Date of Join : 18/12/2010, pass out year : 2021, skill : java, c, python
	 * Ex: Id : 2, Name : uvx, Designation : set, Department : ece,
	 *     phone number : 8901234567, Date of birth : 10/10/1980, Previous Experience in yrs : 0.3,
	 *     Date of Join : 18/12/2010, pass out year : 2022, skill : python, html
	 * </p>
	 *
	 * @param 
	 * @return void
	 */    
	public void displayAllTrainees() {
		if (employeeService.getAllTrainees().isEmpty()) {
			System.out.println("No records!");
		} else {
			List<Trainee> trainees = employeeService.getAllTrainees();
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
	 * <p>
	 * Ex: If we give Employee Id : 5, it will display all the trainee details
	 *         Id : 5, Name : xyz, Designation : set, Department : technical,
	 *         Phone number : 9876543210, Date of birth : 10/10/1980, 
	 *         Previous Experience in yrs : 0.3, Date of Joining : 18/12/2010, 
	 *         passed out year : 2021, skill : java, c, python
	 * </p>
	 *
	 * @param Integer employeeId
	 * @return void
	 */
	public void displayTraineeById(Integer employeeId) {
		if (employeeService.getAllTrainees().isEmpty()){
			System.out.println("No records!");
		} else {
			if (employeeService.checkTraineeById(employeeId)) {
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
	 * <p>
	 * Ex: If we give Employee Id : 5, it shows the trainee details & it ask confirmation
	 *     to update the details. After confirmation, we have to update all the trainee details
	 *         Id : 5, Name : vus, Designation : trainee, Department : cs,
	 *         Phone number : 9876543210, Date of birth : 10/10/1980, 
	 *         Previous Experience in yrs : 0.3, Date of Joining : 18/12/2010, 
	 *         Passed out year : 2021, Skill : python
	 * Ex: If we give Employee Id : 3, it checks the employee id,
	 *     if the employee id is not match, it shows "No Record".
	 * </p>
	 *
	 * @param Integer employeeId
	 * @return void
	 */
	public void updateTraineeById(Integer employeeId) {
		Integer updateOption = 0;
		if (employeeService.getAllTrainees().isEmpty()) {
			System.out.println("No records!");
		} else {
			if (employeeService.checkTraineeById(employeeId)) {
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
	 * <p>
	 * Ex: If we give Employee Id : 2, it will delete all the trainee details in that id,
	 *         and it shows "Deleted"
	 * Ex: If we give Employee Id : I2003, it checks the employee id,
	 *     if the employee id is not match, it shows "No Record".
	 * </p>
	 *
	 * @param Integer employeeId
	 * @return void
	 */
	public void deleteTraineeById(Integer employeeId) {
		Integer updateOption = 0;
		if (employeeService.getAllTrainees().isEmpty()){
			System.out.println("No records!");
		} else {
			if (employeeService.checkTraineeById(employeeId)) {
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
	 * based on Id, Firstname, Lastname, designation, department. 
	 * </p>
	 * <p>
	 * Ex: If we search based on Id : 5
	 *     It displays - Id      First Name Last Name Role
	 *                   5       xyz        abc       Trainer
	 * Ex: If we search based on First Name : uvz
	 *     It displays - Id      First Name Last Name Role
	 *                   2       uvz        uv        Trainee
	 * Ex: If we search based on Designation : set
	 *     It displays - Id      First Name Last Name Role
	 *                   5       xyz        abc       Trainer
	 *                   2       uvz        uv        Trainee
	 * </p>
	 *
	 * @param 
	 * @return void
	 */
	public void searchAnEmployee() {
		//List<Trainer> trainers = new ArrayList<Trainer>();
		//List<Trainer> trainers = employeeService.getAllTrainers();
		//Trainee trainee = new Trainee();
		System.out.println("Search an Employee based on following ways");
		System.out.println("1. First Name\t3. Last Name");
		System.out.print("Enter your way : ");
		Integer searchWay = scanner.nextInt();
		switch (searchWay) {
		case 1: 
			System.out.println("Searching based on First Name");
			System.out.print("Enter Employee First Name : ");
			String firstName = scanner.next();
			List<Trainer> trainers = employeeService.searchTrainerByFirstName(firstName);
			System.out.println(trainers);
			break;
		case 2:
			System.out.println("Searching based on First Name");
			System.out.print("Enter Employee First Name : ");
			String firstName1 = scanner.next();
			List<Trainee> trainees = employeeService.searchTraineeByFirstName(firstName1);
			System.out.println(trainees);
			break;
		case 3: 
			System.out.println("Searching based on Last Name");
			System.out.print("Enter Employee Last Name : ");
			String lastName = scanner.next();
			List<Trainer> trainers1 = employeeService.searchTrainerByLastName(lastName); 
			System.out.println(trainers1);
		case 4:
			System.out.println("Searching based on Last Name");
			System.out.print("Enter Employee Last Name : ");
			String lastName1 = scanner.next();
			List<Trainee> trainees1 = employeeService.searchTraineeByLastName(lastName1);
			System.out.println(trainees1);
			break;
		default:
			System.out.println("Enter Correct Option!");
			break;
		}
	}
}
