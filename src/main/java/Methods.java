import java.util.InputMismatchException;
import java.util.Scanner;

import model.CourseStatus;
import model.GetGender;
import model.GetSchoolYear;
import model.Student;
import model.Teach;

public class Methods extends Menu {

    public static Scanner scan = new Scanner(System.in);
    public static Directory JPA = new Directory();
    public static StudentMethods studMethods = new StudentMethods();
    
    
    public Teach TeacherPath(int choice) {
    	Teach newTeacher = null;
    	switch(choice) {
    	case 1: newTeacher = Login();
    			break;
    	case 2: newTeacher = Register();
    			JPA.addTeacher(newTeacher);
    			break;
    	case 3: System.out.println("Closing Program..."); scan.close(); System.exit(0); 
    	}
    	
    	return newTeacher;
    }
    
    public Teach Register() {
    	String FirstName = "";
    	String LastName = "";
    	int ID = 0;
    	String Password = "";
    	String Gender = "";
    	boolean flag = true;
    	Teach newTeacher = null;
    	
    	System.out.println("*** Register New Teacher ***");
    	
    	while(flag || (ID <= 0 || ID > 9999)) {
    		try {
    			System.out.print("Please Enter ID Number: ");
    			ID = scan.nextInt();		
    			flag = Directory.checkTeacherID(ID);
    			System.err.println(flag ?"ID# Already Exist!!\n": " ");
    		}catch(InputMismatchException ex) {
    			scan.nextLine();
    		}	
    	}
    	
    	scan.nextLine(); //Grabs input from buffer.
    	 
    	
    	
    	while(Password.isBlank()) {
    		System.out.print("Enter Password: ");
    		Password = scan.nextLine();
    	}
    	
    	while(FirstName.isBlank()) {
    		System.out.print("Please Enter First Name: ");
    		FirstName = scan.nextLine();	
    	}
    	
    	while(LastName.isBlank()) {
    		System.out.print("Please Enter Last Name: ");
    		LastName = scan.nextLine();	
    	}
    	
    	while(Gender.isBlank()) {
    		Gender = pickGender().toString();
    	}
    	
    	
    	newTeacher = new Teach();
    	newTeacher.setID(ID);
    	newTeacher.setPassword(Password);
    	newTeacher.setFirst(FirstName);
    	newTeacher.setLast(LastName);
    	newTeacher.setGender(Gender);
    	return newTeacher;
    	
    }
    
    
    public GetGender pickGender() {
    	GetGender gg = null;
    	char choice = ' ';
    	
    	while(choice != 'm' && choice != 'f') {
    		System.out.println("Please Enter Gender(M/F): ");
    		System.out.println("M. " + GetGender.Male);
    		System.out.println("F. " + GetGender.Female);
    		System.out.print("Action: ");
    		choice = scan.next().toLowerCase().charAt(0);
    		
    		switch(choice) {
    		case 'm': gg = GetGender.Male; break;
    		case 'f': gg = GetGender.Female; break;
    		default : System.err.println("Incorrect Input"); break;
    		}
    	}
    	
    	return gg;
    	
    }
    
    @Override
    public Teach Login() {
    	  Teach returningTeacher = null;
    	  int log_id = 0;
    	  String log_password = "";
    	  System.out.println("*** Login ***");
    	  while(log_id <= 0 || log_id > 9999) {
      		try {
      			System.out.print("Please Enter ID Number: ");
      			log_id = scan.nextInt();		
      		}catch(InputMismatchException ex) {
      			scan.nextLine();
      		}	
      	}
    	  scan.nextLine();
      	
      	while(log_password.isBlank()) {
      		System.out.print("Enter Password: ");
      		log_password = scan.nextLine();
      	}
      	
      	returningTeacher = Directory.getTeacher(log_id, log_password);
      	
      	if(returningTeacher == null) {
      		System.err.println("Incorrect Information!");
      	}
      	
      	return returningTeacher;
    }
    
    @Override
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
  	  
  	  teacherOptions(choice, Teacher);
    }
    
    public void teacherOptions(int choice, Teach Teacher) {
		switch(choice) {
		case 1: studMethods.enterNewStudent(Teacher.getID()); break;
		case 2: studMethods.searchOptions(Teacher.getID()); break;
		//case 3: viewAllStudents();
		//case 4: removeStudent();
		case 5: 
		}
		
		TeacherMenu(Teacher);
		
	}
    
   
}