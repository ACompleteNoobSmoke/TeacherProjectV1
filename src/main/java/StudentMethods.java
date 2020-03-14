import java.util.InputMismatchException;
import java.util.Scanner;

import model.CourseStatus;
import model.GetSchoolYear;
import model.Student;
import model.Teach;

public class StudentMethods {
	
	static Scanner scan = new Scanner(System.in);
	static Methods meth = new Methods();
	
	 public void enterNewStudent(int teacherID) {
	    	String f_name = "";
	    	String l_name = "";
	    	int id = 0;
	    	String dob = "";
	    	int age = 0;
	    	String schoolYear = "";
	    	String gender = "";
	    	String status = "";
	    	String hobby = "";
	    	String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
	    	boolean studentIDFlag= true;
	    	
	    	System.out.println("*** Student Information ***");
	    	
	    	while(f_name.isBlank()) {
	    		System.out.print("Please Enter Student's First Name: ");
	    		f_name = scan.nextLine();
	    	}
	    	
	    	while(l_name.isBlank()) {
	    		System.out.print("Please Enter Student's Last Name: ");
	    		l_name = scan.nextLine();
	    	}
	    	
	    	while(studentIDFlag || (id <= 0 || id > 9999)) {
	    		try {
	    			System.out.print("Please Enter Student's ID Number: ");
	    			id = scan.nextInt();		
	    			studentIDFlag = Directory.checkStudentID(id, teacherID);
	    			System.err.println(studentIDFlag ?" Student ID# Already Exist!!\n": " ");
	    		}catch(InputMismatchException ex) {
	    			scan.nextLine();
	    		}	
	    	}
	    	
	    	scan.nextLine();
	    	
	    	while(!dob.matches(regex)) {
	    		System.out.println("Please Enter Student's Date Of Birth(dd/mm/yyy): ");
	    		dob= scan.nextLine();
	    	}
	    	
	    	
			while(age < 1 || age > 99) {
	    		age = getAge();
	    	}
	    	
	    	while(schoolYear.isBlank()) {
	    		schoolYear = pickSchoolYear().toString();
	    	}
	    	
	    	while(gender.isBlank()) {
	    		gender = meth.pickGender().toString();
	    	}
	    	
	    	while(status.isBlank()) {
	    		status = pickCourseStatus().toString();
	    	}
	    	
	    	while(hobby.isBlank()) {
	    		System.out.println("Please Enter Student's Hobby");
	    		System.out.print("Hobby: ");
	    		hobby = scan.nextLine();
	    	}
	    	
	    	Directory.addStudent(new Student(teacherID, f_name, l_name, id, dob, age, 
	    			schoolYear, gender, status, hobby));
	    }
	    
	    

		public GetSchoolYear pickSchoolYear() {
	    	GetSchoolYear gsy = null;
	    	int choice = 0;
	    	int count = 0;
	    	
	    	while(choice < 1 || choice > 5) {
	    		try {
	    		System.out.println("Please Enter Student's School Year: ");
	    		for(GetSchoolYear year: GetSchoolYear.values()) {
	    			System.out.println(++count + " " + year);
	    		}
	    		System.out.print("Action: ");
	    		choice = scan.nextInt();
	    		}catch(InputMismatchException e) {
	    			scan.nextLine();
	    			count = 0;
	    		}
	    	}
	    	
	    	scan.nextLine();
	    	
	    	  switch(choice) {
	    		case 1: gsy = GetSchoolYear.Freshman; break;
	    		case 2: gsy = GetSchoolYear.Sophomore; break;
	    		case 3: gsy = GetSchoolYear.Junior; break;
	    		case 4: gsy = GetSchoolYear.Senior; break;
	    		case 5: gsy = GetSchoolYear.Graduate; break;
	    		}  
	    	  
	    	return gsy;
	    	
	    }
		
		public CourseStatus pickCourseStatus() {
				CourseStatus cs = null;
				int choice = 0;
				
				while(choice < 1 || choice > 2) {
					try {
						System.out.println("Please Enter Student's Status:");
						System.out.println("1. " + CourseStatus.Pass);
						System.out.println("2. " + CourseStatus.Fail);
						System.out.println("Action: ");
						choice = scan.nextInt();
					}catch(InputMismatchException e) {
						scan.nextLine();
					}
				}
				
				scan.nextLine();
				
				if(choice == 1) {
					cs = CourseStatus.Pass;
				}else {
					cs = CourseStatus.Fail;
				}
				
				return cs;
			}
		
		public int getAge() {
			int age = 0;
			
			while(age < 10 || age > 100) {
				try {
				System.out.print("Please Enter Student's Age: ");
				age = scan.nextInt();
				}catch(InputMismatchException e) {
					scan.nextLine();
				}
			}
			
			scan.nextLine();
			
			return age;
		}
		
		public void searchMenu() {
			System.out.println("*** Search Student ***");
			System.out.println("1. Search By Student ID Number");
			System.out.println("2. Search By Student Full Name");
			System.out.println("3. Back");
			System.out.print("Action: ");
		}
		
		public void searchOptions(int teacherID) {
			int choice = 0;
			while(choice < 1 || choice > 3) {
				try {
					searchMenu();
					choice = scan.nextInt();
				}catch(InputMismatchException e) {
					scan.nextLine();
				}
			}
			
			scan.nextLine();
			
			if(choice == 1) {
				viewSearchStudent(searchStudentByID(teacherID));
			}else if(choice == 2){
				viewSearchStudent(searchStudentByFullName(teacherID));
			}	
		}
		
		
		
		public Student searchStudentByID(int teacherID) {
			int studentID = 0;
			Student existingStudent = null;
			while(studentID < 1 || studentID > 9999) {
				try {
					System.out.println("*** Search Student(ID) ***");
					System.out.print("Enter ID Number: ");
					studentID = scan.nextInt();
				}catch(InputMismatchException e) {
					scan.nextLine();
				}
			}
			
			scan.nextLine();
			if(!Directory.checkStudentID(teacherID, studentID)) {
				existingStudent = Directory.getStudent(teacherID, studentID, " ");
			}
			
			return existingStudent;
		}
		
		
		
		public Student searchStudentByFullName(int teacherID) {
			String studentName = "";
			Student existingStudent = null;
			
			while(studentName.isBlank()) {
			System.out.println("*** Search Student(Name) ***");
			System.out.print("Enter Full Name: ");
			studentName = scan.nextLine();
			}
			
			existingStudent = Directory.getStudent(teacherID, 0, studentName);
			
			return existingStudent;
		}
		
		public void viewSearchStudent(Student existingStudent ) {
			System.out.println("*** Student Profile ***\n");
			System.out.println(existingStudent != null? existingStudent: "Student Not Found!!"); 
			System.out.println("\nPress Anything To Continue");
			scan.nextLine();
		}
	

}
