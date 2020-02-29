import java.io.IOException;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Student;
import model.Teach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Menu{

    //Tools used through file
    public static Scanner scan = new Scanner(System.in);
    static boolean check = false;
    static Teach teacher = new Teach();
    static Directory direct = new Directory();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
//#region MAIN MENU DISPLAY 

  
    

    //Method that prints when a new teacher is saved to folders
    public void message(Teach teacher){
        System.out.println("\n       **A MESSAGE FROM PRINCIPAL LIL B THE BASEDGOD**\n");
        System.out.println("Congratulations " + ((teacher.getGenderString() == "Male") ? "Mr. " + teacher.getLast(): "Mrs. " + teacher.getLast()));
        System.out.println("\nWelcome to BASED University, " + teacher.getFirst() + " " + teacher.getLast() + ", your account has been created.");
        System.out.println("You now have the ability to add upcoming or edit student's information that are enrolled in your course.");
        System.out.println("Have a wonderful semester\n\nBASEDGOD OUT!\n\n");
        System.out.println("(Press Anything To Continue)\n\n"); scan.nextLine();

        direct.searchFiles(teacher.getID());
    }

    public void introduction(){
        System.out.println("                             ***Project 1: Teacher Course***\n");
        System.out.println("Name: Omoruyi Omofonmwan\nAge: 26\nBackground: Self-Taught Java Developer\n\n");
        System.out.println("Summary: Hello, This is the first project in the series of projects I aim to develop using Java.");
        System.out.println("I used different techniques in this project such as Encapsulation, String Methods,\n ArrayList, Object Creation/Manipulation as well as Writing & Reading From Files.");
        System.out.println("\nInstructions: When it says \"Action: \" please enter number associated with the action that you wish to take.");
        System.out.println("Any feedback would be greatly appreciated. Thank You\n\n\n**Press Anything To Continue**"); scan.nextLine();
    }

}