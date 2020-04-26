package methods;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


import gui.Menu;

import model.CourseStatus;
import model.GetSchoolYear;
import model.Student;

import repo.Directory;

public class StudentMethods extends Menu {
	
	static Scanner scan = new Scanner(System.in);
	static Methods meth = new Methods();
	
	 public void enterNewStudent(int teacherID) {
	    	
	    	int id = 0;
	    	boolean studentIDFlag= true;
	    	
	    	System.out.println("*** Student Information ***");
	    	
	    	String f_name = InputMethods.inputStudentFirstName();
	    	String l_name = InputMethods.inputLastName();
	    	
	    	while(studentIDFlag) {
	    		id = InputMethods.inputID();		
	    		System.err.println((studentIDFlag = Directory.checkStudentID(id, teacherID))  ?
	    					" \nStudent ID# Already Exist!!\n": " ");
	    	}
	    	
	    	String dob = InputMethods.inputDOB();
	    	int age = InputMethods.inputAge();
	    	String schoolYear = InputMethods.inputSchoolYear().toString();
	    	String gender = InputMethods.inputGender().toString();
	    	String status = InputMethods.inputCourseStatus().toString();
	    	String hobby = InputMethods.inputHobby();
	    	
	    	Directory.addStudent(new Student(teacherID, f_name, l_name, id, dob, age, 
	    			schoolYear, gender, status, hobby));
	    }
	    
	
		public void searchStudents(int teacherID) {
			int choice = 0;
			while(choice < 1 || choice > 3) {
				meth.searchMenuDisplay();
				choice = InputMethods.getChoice();
			}
			
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
				System.out.println("*** Search Student(ID) ***");
				studentID = InputMethods.inputID();
			}
			
			System.out.println("The Teacher ID Is: " + teacherID + "\nThe Student ID Is: " + studentID);
			
			if(!Directory.checkStudentID(teacherID, studentID)) {
				existingStudent = Directory.getStudentByID(teacherID, studentID);
				System.out.println("It should print out a student\n" + existingStudent.getFullname());
			}
			
			return existingStudent;
		}
		
		
		
		public Student searchStudentByFullName(int teacherID) {
			String firstName = "";
			String lastName = "";
			String studentName = "";
			Student existingStudent = null;
			
			while(studentName.isBlank()) {
			System.out.println("*** Search Student(Name) ***");
			firstName = InputMethods.inputStudentFirstName();
			lastName = InputMethods.inputStudentLastName();
			studentName = firstName.concat(" "  + lastName);
			}
			
			existingStudent = Directory.getStudentByFullName(teacherID, studentName);
			
			return existingStudent;
		}
		
		public boolean viewSearchStudent(Student existingStudent ) {
			System.out.println("*** Student Profile ***\n");
			System.out.println(existingStudent != null? existingStudent: "Student Not Found!!"); 
			//System.out.println("\nPress Anything To Continue");
			//scan.nextLine();
			
			return existingStudent == null;
		}
		
		public void viewAllStudents(int teacherID) {
			int choice = 0;
			
			while(choice < 1 || choice > 5) {
				viewAllMenuDisplay();
				choice = InputMethods.getChoice();
			}
	
			if(choice < 5)
				viewAllSwitch(choice, teacherID);
		}
		
		
		public void viewAllSwitch(int choice, int teacherID) {
			ArrayList<Student> listStudent = null;
			switch(choice) {
			case 1: listStudent = viewAllByGender(teacherID); break;
			case 2: listStudent = viewAllBySchoolYear(teacherID); break;
			case 3: listStudent = viewAllByStatus(teacherID); break;
			case 4: listStudent = viewAll(teacherID); break;
			}
			
			displayAllStudents(listStudent);
		}
		
		
		 public ArrayList<Student> viewAllByGender(int teacherID){
			  System.out.println("*** View All(Gender) ***");
			  String getGender = InputMethods.inputGender().toString();
			  ArrayList<Student> getByGender = Directory.getStudentsBy(teacherID, getGender, " ", " ");
			  
			  return getByGender;  
		  }
		 
		 
		  public ArrayList<Student> viewAllBySchoolYear(int teacherID){
			  System.out.println("*** View All(School Year) ***");
			  String getSchoolYear = InputMethods.inputGender().toString();
			  ArrayList<Student> getAllByYear = Directory.getStudentsBy(teacherID, " ", getSchoolYear, " ");
			  
			  return getAllByYear;  
		  }
		  
		  public ArrayList<Student> viewAllByStatus(int teacherID){
			  System.out.println("*** View All(Status) ***");
			  String getStatus = InputMethods.inputCourseStatus().toString();
			  ArrayList<Student> getAllByStatus = Directory.getStudentsBy(teacherID, " ", " ", getStatus);
			  
			  return getAllByStatus;  
		  }
		  
		  public ArrayList<Student> viewAll(int teacherID){
			  ArrayList<Student> getAllStudents = Directory.getStudents(teacherID);
			  return getAllStudents;  
		  }
		 
		 public void displayAllStudents(ArrayList<Student> studentList) {
			 System.out.println("\t\t\t*** All Student ***\n");
			 if(studentList == null) {
				 System.out.println("No Student Is Currently Enrolled In This Course!");
			 }else {
				 viewAll(studentList);
			 }
			 
			System.out.println("\nPress Anything To Continue\n");
			scan.nextLine(); 
		 }
		 
		 public void viewAll(ArrayList<Student> studentList) {
			 boolean header = true;
			 for(Student student : studentList) {
				 if(header) {
					 student.header();
					 header = false;
				 }
				 System.out.println(student.printAll());
			 }
		 }
		 
		 public void removeStudent(int teacherID) {
			 int choice = 0;
			 Student foundStudent = null;
				while(choice < 1 || choice > 3) {
					meth.searchMenuDisplay();
					choice = InputMethods.getChoice();
				}
				
				
				if(choice == 1) {
					foundStudent = searchStudentByID(teacherID);
					removeOptions(teacherID, foundStudent);
				}else if(choice == 2){
					foundStudent = searchStudentByFullName(teacherID);
					removeOptions(teacherID, foundStudent);
				}	
				
				
		 }
		 
		 public void  removeOptions(int teacherID, Student foundStudent) {	 
			 if(!viewSearchStudent(foundStudent)) {
				 deleteOptions(teacherID, foundStudent);
			 } 
		 }
		 
		 public void deleteOptions(int teacherID, Student foundStudent) {
			 int choice = 0;
			 while(choice < 1 || choice > 2) {
				 viewSearchStudent(foundStudent);
				 deleteOptionMenuDisplay();
				 choice = InputMethods.getChoice(); 
			 }
			
			 
			 if(choice == 1) {
				 Directory.deleteStudent(teacherID, foundStudent.getId());
				 System.out.println(foundStudent.getFullname() + " Has Been Deleted");
			 }
		 }
		 
		 
}
