package gui;
 
import java.util.Scanner;

import model.CourseStatus;
import model.GetGender;
import model.Teach;


public class Menu{

    //Tools used through class
    public static Scanner scan = new Scanner(System.in);
    static Teach teacher = new Teach();
    
    //Introduction to myself and the project
    public void introductionDisplay(){
        System.out.println("                             ***Project 1: Teacher Course(Updated)***\n");
        System.out.println("Name: Omoruyi Omofonmwan\nAge: 26\nBackground: Self-Taught Java Developer\n\n");
        System.out.println("Summary: Hello, this is an updated version of my first project.");
        System.out.println("I used different techniques in this project such as Encapsulation, String Methods,\n ArrayList, Object Creation/Manipulation as well as saving to a Database using JPA.");
        System.out.println("\nInstructions: When it says \"Action: \" please enter number associated with the action that you wish to take.");
        System.out.println("Any feedback would be greatly appreciated. Thank You\n\n\n**Press Anything To Continue**"); scan.nextLine();
    }
    
    
  //Method that prints when a new teacher is saved to the database
    public Teach messageDisplay(Teach teacher){
        System.out.println("\n       **A MESSAGE FROM PRINCIPAL LIL B THE BASEDGOD**\n");
        System.out.println("Congratulations " + ((teacher.getGender() == "Male") ? "Mr. " + teacher.getLast(): "Mrs. " + teacher.getLast()));
        System.out.println("\nWelcome to BASED University, " + teacher.getFirst() + " " + teacher.getLast() + ", your account has been created.");
        System.out.println("You now have the ability to add upcoming or edit student's information that are enrolled in your course.");
        System.out.println("Have a wonderful semester\n\nBASEDGOD OUT!\n\n");
        System.out.println("(Press Anything To Continue)\n\n"); scan.nextLine();
        
        return teacher;
    }
    
     
//#region MENU GUI DISPLAY 

  public void mainMenuDisplay() {
		System.out.println("*** Main Menu ***");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
		System.out.print("\nAction: ");  
		}
  
  
  public void teacherMenuDisplay() {
		System.out.println("1. Enter New Student");
		System.out.println("2. Search Student");
		System.out.println("3. View All Students");
		System.out.println("4. Remove Student");
		System.out.println("5. Log Out");
		System.out.print("\nAction: ");
		}
  
  public void searchMenuDisplay() {
		System.out.println("*** Search Student ***");
		System.out.println("1. Search By Student ID Number");
		System.out.println("2. Search By Student Full Name");
		System.out.println("3. Back");
		System.out.print("\nAction: ");
		}
  
  public void genderMenu() {
	  System.out.println("Please Enter Gender(M/F): ");
	  System.out.println("M. " + GetGender.Male);
	  System.out.println("F. " + GetGender.Female);
	  System.out.print("\nAction: ");
  }
  
  public void courseStatusMenu() {
	  System.out.println("Please Enter Student's Status:");
	  System.out.println("1. " + CourseStatus.Pass);
	  System.out.println("2. " + CourseStatus.Fail);
	  System.out.println("\nAction: ");
  }
  
  public void viewAllMenuDisplay() {
	  System.out.println("*** View Options ***");
	  System.out.println("1. View By Gender");
	  System.out.println("2. View By School Year");
	  System.out.println("3. View By Status");
	  System.out.println("4. View All Students");
	  System.out.println("5. Back");
	  System.out.print("\nAction: ");
  }
  
  public void deleteOptionMenuDisplay() {
	  System.out.println("\n*** Delete Options ***");
	  System.out.println("1. Delete Student");
	  System.out.println("2. Cancel");
	  System.out.print("\nAction: ");
  }
  
  //#endregion MENU GUI DISPLAY//
  
  
  //#region BASEDGOD GUI DISPLAY//
  
  public void principalMenuDisplay() {
	  System.out.println("\n*** Principal BASEGOD ***");
	  System.out.println("1. Register");
	  System.out.println("2. Search");
	  System.out.println("3. View");
	  System.out.println("4. ReAssign");
	  System.out.println("5. Delete");
	  System.out.print("\nAction: ");
  }
  
  public void principalRegisterDisplay() {
	  System.out.println("*** Registration Option ***");
	  System.out.println("1. Register Teacher");
	  System.out.println("2. Register Student");
	  System.out.println("3. Back");
	  System.out.print("\nAction: ");
  }
  
  public void principalSearchDisplay() {
	  System.out.println("*** Search Option ***");
	  System.out.println("1. Search Teacher");
	  System.out.println("2. Search Student");
	  System.out.println("3. Back");
	  System.out.print("\nAction: ");
  }
  
  public void principalViewAllDisplay() {
	  System.out.println("*** View Option ***");
	  System.out.println("1. View All Teacher");
	  System.out.println("2. View All Student");
	  System.out.println("3. Back");
	  System.out.println("\nAction: ");
  }
}