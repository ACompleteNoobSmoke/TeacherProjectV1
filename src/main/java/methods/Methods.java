package methods;

import java.util.Scanner;

import gui.Menu;
import model.Teach;
import repo.Directory;

public class Methods extends Menu {

    public static Scanner scan = new Scanner(System.in); //Used for user input for the projects
    public static Directory JPA = new Directory(); //Tool for using JPA connection to the database
    public static StudentMethods studMethods = new StudentMethods();  //Sends the teacher to be able to modify student information.
    
    public int mainMenu() {
    	int choice = 0;
    	while(choice < 1 || choice > 3) {
    		mainMenuDisplay();
    		choice = InputMethods.getChoice();
    	}
    	return choice;	
    }
      
    public Teach teacherPath(int choice) {
    	Teach newTeacher = null;
    	switch(choice) {
    	case 1: newTeacher = login();
    			break;
    	case 2: newTeacher = register();
    			JPA.addTeacher(newTeacher);
    			break;
    	case 3: System.out.println("Closing Program..."); scan.close(); System.exit(0); 
    	}
    	return newTeacher;
    }
    
    
    public Teach login() {
  	  Teach returningTeacher = null;
  	  
  	  System.out.println("*** Login ***");
  	  int log_id = InputMethods.inputID();
  	  String log_password = InputMethods.inputPassword();
    	
      returningTeacher = Directory.getTeacher(log_id, log_password);
    	
    	if(returningTeacher == null) {
    		System.err.println("Incorrect Information!");
    	}else if(returningTeacher.getID() == 9999) {
    		
    	}
    	
    	return returningTeacher;
  }
  
    
   
   
    public Teach register() {
    	int ID = 0;
    	boolean flag = true;
    	
    	System.out.println("*** Register New Teacher ***");
    	
    	while(flag) {
    		ID = InputMethods.inputID();		
    		flag = Directory.checkTeacherID(ID);
    		System.err.println(flag ?"ID# Already Exist!!\n": " ");	
    	}
    	 
    	String FirstName = InputMethods.inputFirstName();
    	String LastName = InputMethods.inputLastName();
    	String Password = InputMethods.inputPassword();
    	String Gender = InputMethods.inputGender().toString();
  
    	Teach newTeacher = new Teach(ID, Password, FirstName, LastName, Gender);
    	messageDisplay(newTeacher);
    	return newTeacher;
    	
    }
    
    
    public void teacherMenu(Teach Teacher) {
  	  int choice = 0;
  	  String title = Teacher.getGender().equalsIgnoreCase("male") ? "Mr. " : "Mrs. ";
  	  
  	  while(choice < 1 || choice > 5) {
  		System.out.println("*** " + title.concat(Teacher.getLast()) +  " Course ***");
  		teacherMenuDisplay();
  		choice = InputMethods.getChoice();  
  	  } 
  	  
  	  if(choice >= 1 && choice < 5) {
  		  teacherOptions(choice, Teacher);
  	  }
  	  
    }
    
    
    
    public void teacherOptions(int choice, Teach Teacher) {
		switch(choice) {
		case 1: studMethods.enterNewStudent(Teacher.getID()); break;
		case 2: studMethods.searchStudents(Teacher.getID()); break;
		case 3: studMethods.viewAllStudents(Teacher.getID()); break;
		case 4: studMethods.removeStudent(Teacher.getID()); break;
		}
		teacherMenu(Teacher);	
	}
    
   
}