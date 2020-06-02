package methods;

import java.util.ArrayList;

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
		while(pick <= 0 || pick > 6) {
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
		while(optionPicked > 0 && optionPicked < 6) {
		  switch(optionPicked) {
				case 1: registerAction(); break;
				case 2: searchAction(); break;
				case 3: viewAction(); break;
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

		boolean header = true;
			for(Student student : view) {
				if(header) {
					student.header();
					header = false;
				}
				System.out.println(student.printAll());
			}

	}
	

}
