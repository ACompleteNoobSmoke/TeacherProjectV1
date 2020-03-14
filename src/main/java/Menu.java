 
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Teach;

 

public class Menu{

    //Tools used through file
    public static Scanner scan = new Scanner(System.in);
    static boolean check = false;
    static Teach teacher = new Teach();
     
    
//#region MAIN MENU DISPLAY 

  public int MainMenu() {
	  int choice = 0;
	  while(choice < 1 || choice > 3) {
		  try {
			  System.out.println("*** Main Menu ***");
			  System.out.println("1. Login");
			  System.out.println("2. Register");
			  System.out.println("3. Exit");
			  System.out.print("Action: ");
			  choice = scan.nextInt();
			  scan.nextLine();
		  }catch(InputMismatchException ex) {
			  scan.nextLine();
		  }
	  } 
	  
	  return choice;
  }
  
  public Teach Login() {
	  Teach returningTeacher = null;
	  int log_id = 0;
	  String log_password = " ";
	  System.out.println("*** Login ***");
	  while(log_id <= 0 || log_id > 9999) {
  		try {
  			System.out.print("Please Enter ID Number: ");
  			log_id = scan.nextInt();		
  		}catch(InputMismatchException ex) {
  			scan.nextLine();
  		}	
  	}
  	
  	while(log_password.isBlank()) {
  		System.out.print("Enter Password: ");
  		log_password = scan.nextLine();
  	}
  	
  	return returningTeacher;
  }
  
  public void TeacherMenu(Teach Teacher) {
	  int choice = 0;
	  String title = Teacher.getGender().equalsIgnoreCase("male") ? "Mr. " : "Mrs. ";
	  while(choice < 1 || choice > 3) {
		  try {
			  System.out.println("*** " + title.concat(Teacher.getLast()) +  " Course ***");
			  System.out.println("1. Enter New Student");
			  System.out.println("2. Search Student");
			  System.out.println("3. View All Students");
			  System.out.println("4. Remove Student");
			  System.out.println("5. Log Out");
			  System.out.print("Action: ");
			  choice = scan.nextInt();
			  scan.nextLine();
		  }catch(InputMismatchException ex) {
			  scan.nextLine();
		  }
	  } 
  }
  
  
  
 
    

    //Method that prints when a new teacher is saved to folders
    public Teach message(Teach teacher){
        System.out.println("\n       **A MESSAGE FROM PRINCIPAL LIL B THE BASEDGOD**\n");
        System.out.println("Congratulations " + ((teacher.getGender() == "Male") ? "Mr. " + teacher.getLast(): "Mrs. " + teacher.getLast()));
        System.out.println("\nWelcome to BASED University, " + teacher.getFirst() + " " + teacher.getLast() + ", your account has been created.");
        System.out.println("You now have the ability to add upcoming or edit student's information that are enrolled in your course.");
        System.out.println("Have a wonderful semester\n\nBASEDGOD OUT!\n\n");
        System.out.println("(Press Anything To Continue)\n\n"); scan.nextLine();
        
        return teacher;

      
    }

    public void introduction(){
        System.out.println("                             ***Project 1: Teacher Course(Updated)***\n");
        System.out.println("Name: Omoruyi Omofonmwan\nAge: 26\nBackground: Self-Taught Java Developer\n\n");
        System.out.println("Summary: Hello, This is the first project in the series of projects I aim to develop using Java.");
        System.out.println("I used different techniques in this project such as Encapsulation, String Methods,\n ArrayList, Object Creation/Manipulation as well as Writing & Reading From Files.");
        System.out.println("\nInstructions: When it says \"Action: \" please enter number associated with the action that you wish to take.");
        System.out.println("Any feedback would be greatly appreciated. Thank You\n\n\n**Press Anything To Continue**"); scan.nextLine();
    }
    
    

}