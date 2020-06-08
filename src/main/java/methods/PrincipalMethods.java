package methods;

import java.util.ArrayList;
import java.util.Random;

import CompareMethods.SortingMethods;
import gui.Menu;
import model.Student;
import model.Teach;
import repo.Directory;

public class PrincipalMethods {
	
	static Menu menu = new Menu();
	Methods teacherMethods = new Methods();
	Directory directory = new Directory();
	StudentMethods sm = new StudentMethods();
	
	public int principalChoice() {
		int pick = 0;
		while(pick <= 0 || pick > 5) {
			menu.principalMenuDisplay();
			pick = InputMethods.getInt();
		}
		System.out.println("");
		return pick;
	}
	
	public int registerChoice() {
		int registerPick = 0;
		while(registerPick <= 0 || registerPick > 3) {
			menu.principalRegisterDisplay();
			registerPick = InputMethods.getInt();
		}
		return registerPick;
	}
	
	public void principalOptions() {
		int optionPicked = principalChoice();
		while(optionPicked > 0 && optionPicked < 5) {
		  switch(optionPicked) {
				case 1: registerAction(); break;
				case 2: searchAction(); break;
				case 3: viewAction(); break;
				case 4: deleteAction(); break;
		 }
		optionPicked = principalChoice();
	  }
		System.out.println("BASEDGOD OUT....\n");
	}
	
	public void registerAction() {
		int registerPicked = registerChoice();
		if(registerPicked == 1) {
			createNewTeacher();
		}else if(registerPicked == 2) {
			createNewStudent();
		}
	}
	
	public void createNewTeacher() {
		Teach newTeacher = teacherMethods.register();
		directory.addTeacher(newTeacher);
	}
	
	public ArrayList<Teach> viewAllTeachers() {
		ArrayList<Teach> allTeachers = Directory.getTeachers();
		if(allTeachers.isEmpty()) {
			System.out.println("No Teacher Registered\n");
			return null;
		}
		
		boolean header = true;
		 for(Teach teachers : allTeachers) {
			 if(header) {
				 teachers.header();
				 header = false;
			 }
			 System.out.println(teachers.printAll());
		 }
		 return allTeachers;
	}
	
	public int pickTeacher() {
		ArrayList<Teach> teachers = viewAllTeachers();
		if( teachers == null) {
			return 0;
		}
		
		int teacherID = verifyTeacherID();
		return teacherID;
	}
	
	public int verifyTeacherID() {
		int teacherID = 0;
		ArrayList<Teach> teachers = Directory.getTeachers();
		
		while(teacherID == 0) {
		  System.out.print("\nEnter Teacher ID Number: ");
		  teacherID = InputMethods.getInt();
		  for(Teach teach: teachers) {
			  if(teacherID == teach.getID()) {
				  return teacherID;
			  }
		  }
		  teacherID = 0;
		}
		return 0;
	}
	
	public void createNewStudent() {
		int teacherID = pickTeacher();
		if(teacherID == 0) {
			return;
		}
		
		sm.enterNewStudent(teacherID);
	}
	
	public int pickSearch() {
		int searchPick = 0;
		while(searchPick <= 0 || searchPick > 3) {
			menu.principalSearchDisplay();
			searchPick = InputMethods.getInt();
		}
		return searchPick;
	}
	
	public void searchAction() {
		int choice = pickSearch();
		if(choice == 1) {
			viewSearchedTeacher(1);
		}else if(choice == 2) {
			viewSearchedStudent();
		}
	}
	
	public int searchOptions(int header) {
		int secondChoice = 0;
		while(secondChoice <= 0 || secondChoice > 3) {
		menu.principalSearchOptionsDisplay(header);
		secondChoice = InputMethods.getInt();
		}
		
		return secondChoice;
	}
	
	public Teach searchTeachByID() {
		System.out.println("*** Search Teacher(ID) ***");
		int teacherID = InputMethods.inputID();
		Teach foundTeacherByID = Directory.searchTeacherID(teacherID);
		return foundTeacherByID;
	}
	
	public Teach searchTeachByName() {
		System.out.println("*** Search Teacher(Name) ***");
		String teacherFirstName = InputMethods.inputFirstName();
		String teacherLastName = InputMethods.inputLastName();
		Teach foundTeacherByName = Directory.searchTeacherName(teacherFirstName, teacherLastName);
		return foundTeacherByName;
	}
	
	public void viewSearchedTeacher(int header) {
		int viewSearchPick = searchOptions(header);
		Teach viewTeacher = null;
		
		if(viewSearchPick == 3) {
			return;
		}else if(viewSearchPick == 1) {
			viewTeacher = searchTeachByID();
		}else {
			viewTeacher = searchTeachByName();
		}
		
		System.out.println(viewTeacher == null? "Teacher Not Found!\n" : viewTeacher.searchString());
	}
	
	public void viewSearchedStudent() {
		
		int teacherID = pickTeacher();
		sm.searchStudents(teacherID);
	}
	
	public void viewAction() {
		int viewPick = viewMenuOptions();
		if(viewPick == 1) {
			viewingTeacherList();
		}else if(viewPick == 2) {
			viewStudentsList();
		}
	}
	
	public int viewMenuOptions() {
		int viewPick = 0;
		while(viewPick < 1 || viewPick > 3) {
		menu.principalViewAllDisplay();
		viewPick = InputMethods.getInt();
		}
		return viewPick;
	}
	
	public int viewTeacherPick() {
		int viewPickTeacher = 0;
		while(viewPickTeacher < 1 || viewPickTeacher > 3) {
			menu.principalViewTeachersDisplay();
			viewPickTeacher = InputMethods.getInt();
		}
		return viewPickTeacher;
	}
	
	public ArrayList<Teach> viewingTeacherList(int pick) {
		ArrayList<Teach> allTeachers = null;
		if(pick == 1) {
			System.out.println("*** View All Teachers(Gender) ***");
			String getGender = InputMethods.inputGender().toString();
			allTeachers = Directory.getTeachersByGender(getGender);
		}else if(pick == 2) {
			System.out.println("*** View All Teachers ***");
			allTeachers = Directory.getTeachers();
		}
		return allTeachers;
	}
	
	public void viewingTeacherList() {
		int viewingOption = viewTeacherPick();
		if(viewingOption < 3) {
			ArrayList<Teach> viewAllTeachers = viewingTeacherList(viewingOption);
				if(viewAllTeachers.isEmpty()) {
					System.out.println("No Teacher Registered\n");
					return;
				}
		
		boolean header = true;
		 for(Teach teachers : viewAllTeachers) {
			 if(header) {
				 teachers.header();
				 header = false;
			 }
			 System.out.println(teachers.printAll());
		 }
		
	}
	
	}
	
	public int viewStudentOption() {
		int pick  = 0;
		while(pick < 1 || pick > 6) {
			menu.principalViewStudentsDisplay();
			pick = InputMethods.getInt();
		}
		return pick;
	}
	
	public void viewStudentsList() {
		int optionPick = viewStudentOption();
		if(optionPick != 6) {
		ArrayList<Student> viewStudent = null;
		switch(optionPick) {
		case 1: viewStudent = viewByTeacher(); break;
		case 2: viewStudent = viewStudentByGender(); break;
		case 3: viewStudent = viewStudentByYear(); break;
		case 4: viewStudent = viewStudentByStatus(); break;
		case 5: viewStudent = viewAllStudents(); break;
		}
		viewStudentsPage(viewStudent);
	  }
	}
	
	
	public ArrayList<Student> viewByTeacher(){
		int teacherID = pickTeacher();
		ArrayList<Student> studentClass = sm.viewAll(teacherID);
		return studentClass;
	}
	
	public ArrayList<Student> viewStudentByGender(){
		String gender = InputMethods.inputGender().toString();
		ArrayList<Student> studentsByGender = Directory.getStudentsByGender(gender);
		return studentsByGender;
	}
	
	public ArrayList<Student> viewStudentByYear(){
		String schoolYear = InputMethods.inputSchoolYear().toString();
		ArrayList<Student> studentsByYear = Directory.getStudentsByYear(schoolYear);
		return studentsByYear;
	}
	
	public ArrayList<Student> viewStudentByStatus(){
		String schoolStatus = InputMethods.inputCourseStatus().toString();
		ArrayList<Student> studentsByStatus = Directory.getStudentsByStatus(schoolStatus);
		return studentsByStatus;
	}
	
	public ArrayList<Student> viewAllStudents(){
		ArrayList<Student> everyStudent = Directory.getAllStudents();
		return everyStudent;
	}
	
	
	public void viewStudentsPage(ArrayList<Student> view) {
		if(view.isEmpty()) {
			System.out.println("No Student Assigned Yet!\n");
			return;
		}
		
		view = SortingMethods.sortStudentForPrincipal(view);
		boolean header = true;
			for(Student student : view) {
				if(header) {
					student.header();
					header = false;
				}
				System.out.println(student.printAll());
			}
	}
	
	public int deleteOptions() {
		int choice = 0;
		while(choice < 1 || choice > 3) {
			menu.principalDeleteDisplay();
			choice = InputMethods.getInt();
		}
		return choice;
	}
	
	public void deleteAction() {
		int choice = deleteOptions();
		if(choice == 1) {
			reassignStudents();
		}else if(choice == 2) {
			deleteStudent();
		}
	}
	
	
	public void reassignStudents() {
		int teacherID = pickTeacher();
		boolean delete = false;
		ArrayList<Student> studentList = sm.viewAll(teacherID);
		if(studentList.isEmpty()) {
			System.out.println("Course Is Currently Empty");
			return;
		}
		int newTeacherID = getNewTeacherID(teacherID);
		delete = changeStudentIDs(newTeacherID, studentList);
		if(delete) {
			Directory.deleteTeacher(teacherID);
			System.out.println("\nTeacher Has Been Removed From Database\n");
		}
	}
	
	public boolean changeStudentIDs(int newTeacherID, ArrayList<Student> oldList) {
		
		for(int i = 0; i < oldList.size(); i++) {
			Student oldStudent = oldList.get(i);
			int oldID = oldStudent.getId();
			int oldTeacher = oldStudent.getTeacherid();
			if(Directory.checkStudentID(newTeacherID, oldID)) {
				int changeID = compareIDs(oldID, newTeacherID);
				if(oldID == changeID) {
					System.out.println("New Course Is Full");
					System.out.println("Cannot Remove Teacher Until Replacemet Has Been Found!");
					return false;
				}
				directoryMethod(oldID, changeID, oldTeacher, newTeacherID);
				
			    System.out.println(oldStudent.getFullname() + " ID#: " + oldID + " New ID#: " + changeID);
			}else {			System.out.println("ID Remains The Same");}
		}
		
		return true;
	}
	
	public void directoryMethod(int oldID, int changeID, int oldTeacher, int newTeacherID) {
		Directory.reAssignStudents(oldID, oldTeacher,  0);
		Directory.changeStudentsID(oldID, changeID, 0);
		Directory.reAssignStudents(changeID, 0,  newTeacherID);
		Directory.deleteStudent(oldTeacher, oldID);
		Directory.deleteStudent(0, oldID);
		Directory.deleteStudent(0, changeID);
	}
	
	public int compareIDs(int oldStudent, int newTeacherID) {
		int id = oldStudent;
		ArrayList<Student> newList = sm.viewAll(newTeacherID);
		Random rand = new Random();
		int c = 1;
		while(c == 1) {
			c = 0;
			for(Student student : newList) {
				if(id == student.getId()) {
					id = rand.nextInt(9999);
					c = 1;
				}
			}
			if(c == 0) {
				return id;
			}
		}
		
		return id;
	}
	
	public int getNewTeacherID(int teacherID) {
		int newTeacher = teacherID;
		System.out.println("***ReAssign Students ***");
		while(newTeacher == teacherID) {
			newTeacher = pickTeacher();
		}
		return newTeacher;
	}
	
	public void deleteStudent() {
		boolean found = false;
		int teacherID = pickTeacher();
		int studentID = 0;
		ArrayList<Student> studentClass = sm.viewAll(teacherID);
		if(studentClass.isEmpty()) {
			System.out.println("Class Is Currently Empty");
			return;
		}
		while(!found) {
		viewStudentsPage(studentClass);
		System.out.print("Enter Student ID: ");
		studentID = InputMethods.getInt();
		found = Directory.checkStudentID(teacherID, studentID);
		}
		
		Directory.deleteStudent(teacherID, studentID);
		
	}
	
	
	

}
