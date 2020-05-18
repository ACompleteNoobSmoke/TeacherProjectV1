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
			System.out.println("Under Construction: Students");
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

}
