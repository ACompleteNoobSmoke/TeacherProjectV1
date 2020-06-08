package methods;

import java.util.InputMismatchException;
import java.util.Scanner;

import gui.Menu;
import model.CourseStatus;
import model.GetGender;
import model.GetSchoolYear;

public class InputMethods {
	
	static Scanner scan = new Scanner(System.in);
	static String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
	static Menu menu = new Menu();
	
	public static int getChoice() {
		int choice = 0;
		try {
			choice = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e) {
			scan.nextLine();
		}
		
		return choice;
	}
	
	public static int inputID() {
		int ID = 0;
		while(ID <=0 || ID > 9999) {
			try {
				System.out.print("Please Enter ID Number: ");
    			ID = scan.nextInt();
    			scan.nextLine();
			}catch(InputMismatchException e) {
				scan.nextLine();
			}
		}
		return ID;
	}
	
	public static String inputFirstName() {
		String FirstName = "";
		while(FirstName.isBlank()) {
    		System.out.print("Please Enter First Name: ");
    		FirstName = scan.nextLine();	
    	}
		return FirstName;
	}
	
	public static String inputLastName() {
		String LastName = "";
		while(LastName.isBlank()) {
    		System.out.print("Please Enter Last Name: ");
    		LastName = scan.nextLine();	
    	}
		return LastName;
	}
	
	public static String inputPassword() {
		String Password = "";
		while(Password.isBlank()) {
			System.out.print("Please Enter Password: ");
			Password = scan.nextLine();
		}
		return Password;
	}
	
	 public static GetGender inputGender() {
	    	GetGender gg = null;
	    	char choice = ' ';
	    	
	    	while(choice != 'm' && choice != 'f') {
	    		menu.genderMenu();
	    		choice = scan.next().toLowerCase().charAt(0);
	    	}
	    	
	    	gg = choice == 'm' ? GetGender.Male : GetGender.Female;
	    	
	    	return gg;
	    }
	 
	 public static String inputStudentFirstName() {
			String StudentFirstName = "";
			while(StudentFirstName .isBlank()) {
	    		System.out.print("Please Enter Student's First Name: ");
	    		StudentFirstName  = scan.nextLine();	
	    	}
			return StudentFirstName ;
	}
	 
	 public static String inputStudentLastName() {
			String StudentLastName  = "";
			while(StudentLastName .isBlank()) {
	    		System.out.print("Please Enter Student's Last Name: ");
	    		StudentLastName  = scan.nextLine();	
	    	}
			return StudentLastName ;
	}
	 
	 public static String inputDOB() {
		 String dob = "";
		 while(!dob.matches(regex)) {
	    	System.out.println("Please Enter Student's Date Of Birth(mm/dd/yyyy): ");
	    	dob= scan.nextLine();
	    }
		 
		 return dob;
		 
	 }
	 
	 public static int inputAge() {
			int age = 0;
			
			while(age < 10 || age > 100) {
				try {
				System.out.print("Please Enter Student's Age: ");
				age = scan.nextInt();
				scan.nextLine();
				}catch(InputMismatchException e) {
					scan.nextLine();
				}
			}
			
			return age;
		}
	 
	 public static GetSchoolYear inputSchoolYear() {
	    	int choice = 0;
	    	
	    	while(choice < 1 || choice > 5) {
	    		System.out.println("Please Enter Student's School Year: ");
	    		int count = 0;
	    		for(GetSchoolYear year: GetSchoolYear.values()) {
	    			System.out.println(++count + " " + year);
	    		}
	    		System.out.print("Action: ");
	    		choice = InputMethods.getChoice();
	    	}
	    	
	    	return switchCaseYear(choice);
	    	
	    }
	 
	 public static GetSchoolYear switchCaseYear(int choice) {
		 GetSchoolYear gsy = null;
		 switch(choice) {
	    	case 1: gsy = GetSchoolYear.Freshman; break;
	    	case 2: gsy = GetSchoolYear.Sophomore; break;
	    	case 3: gsy = GetSchoolYear.Junior; break;
	    	case 4: gsy = GetSchoolYear.Senior; break;
	    	case 5: gsy = GetSchoolYear.Graduate; break;
	    } 
		 return gsy;
	 }
	 
	 public static CourseStatus inputCourseStatus() {
			CourseStatus cs = null;
			int choice = 0;
			
			while(choice < 1 || choice > 2) {
				menu.courseStatusMenu();
				choice = getChoice();
			}
			
			cs = choice == 1 ? CourseStatus.Pass : CourseStatus.Fail;
			
			return cs;
		}
	 
	 public static String inputHobby() {
		 String hobby = "";
		 
		 while(hobby.isBlank()) {
	    		System.out.println("Please Enter Student's Hobby");
	    		System.out.print("Hobby: ");
	    		hobby = scan.nextLine();
	    	}
		 
		 return hobby;
	 }
	 
	 public static int getInt() {
		 int newInt = 0;
		 try {
			 newInt = scan.nextInt();
			 scan.nextLine();
		 }catch(InputMismatchException e) {
			 scan.nextLine();
		 }
		 
		 return newInt;
	 }
	 
	
	

}
