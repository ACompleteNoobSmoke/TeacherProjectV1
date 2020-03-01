import java.util.InputMismatchException;
import java.util.Scanner;

import model.GetGender;
import model.Teach;

public class Methods extends Menu {

    public static Scanner scan = new Scanner(System.in);
    
    
    
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
    			flag = Directory.getTeacher(ID);
    			System.err.println(flag ?"ID# Already Exist!!\n": " ");
    		}catch(InputMismatchException ex) {
    			scan.nextLine();
    		}	
    	}
    	
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

}