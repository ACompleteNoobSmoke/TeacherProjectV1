import java.io.IOException;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu{

    //Tools used through file
    public static Scanner scan = new Scanner(System.in);
    static boolean check = false;
    static Teach teacher = new Teach();
    static Directory direct = new Directory();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    static Date date = new Date();
    

    //Main Menu Page
    public void mainmenu(){
        boolean repeat = true;
        direct.createFolder();

        int i = 0;
        while(repeat){
        
        try{
        System.out.println("\n\n****MAIN MENU****\n");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("\nAction: "); 
        i = scan.nextInt(); 
        scan.nextLine();

        }catch(InputMismatchException e){
            scan.nextLine();
            System.err.println("Action Not Recognized!!!\n");
        }

       choice(i);
    }

     

    }

    //Switch case of options
    public  void choice(int i){
        switch(i){
            case 1: 
            regTeacher();
            break;

            case 2:
            login();
            break;

        default:
            System.err.println("Action Not Recognized!!!\n");
            mainmenu();
            break;
        }
    }

    // Method to register new teachers
    public void regTeacher() {
        boolean sec = false;
        boolean gen2 = false;
        String first = "";
        String last = "";
        char gen;
        int id = 0;
        String id2 = "";

        System.out.println("\n\n****Teacher Registration****\n");
        while (first.equals("")) {
            System.out.print("Please Enter First Name: ");
            first = scan.nextLine().trim();
        }

        while (last.equals("")) {
            System.out.print("Please Enter Last Name: ");
            last = scan.nextLine();
        }

        while (!sec) {
            System.out.print("Please Enter Gender(M/F): ");
            gen = scan.next().charAt(0);
            scan.nextLine();
            if (gen == 'M' || gen == 'm') {
                gen2 = true;
                sec = true;
            } else if (gen == 'F' || gen == 'f') {
                gen2 = false;
                sec = true;
            }
        }

        while (id2.length()< 4 || id2.length() > 4) {
            System.out.println("**(Note: First Digit Has To Be Greater Than 0**)");
            System.out.print("Please Enter ID Number(####): ");
            id = scan.nextInt();
            scan.nextLine();
            id2 = Integer.toString(id);
            System.out.println(id2);
        }

        int a = 0;
        while (a < 1 || a > 2) {
            try {
                System.out.println("\n**Option**");
                System.out.println("1. Save & Continue");
                System.out.println("2. Cancel");
                System.out.print("Action: ");
                a = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Action Not Recognized\n");
                a = 0;
                scan.nextLine();
            }

            switch (a) {
            case 1:
                teacher.setFirst(first);
                teacher.setLast(last);
                if (gen2 == true) {
                    teacher.setGender(true);
                    teacher.setGenderString("Male");
                } else if (gen2 == false) {
                    teacher.setGender(false);
                    teacher.setGenderString("Female");
                }
                teacher.setID(id);
                try {
                    direct.createFile(teacher);
                } catch (IOException e) {
                    System.out.println("Error Occured\n");
                    regTeacher();
                }
                break;

            case 2:
                mainmenu();
                break;
            }

        }
    }

    public static void login() {

        int id = 0;
        System.out.println("\n\n****LOGIN****\n");
        while(id <1000 || id > 9999){
        System.out.print("Enter ID(####): "); 
        id = scan.nextInt(); scan.nextLine();
        }

        direct.searchFiles(id);
    }


    public void menu(Teach teacher){
         int a = 0;
        while(a <1 || a > 4){
        try{
        System.out.println("*****Professor Menu*****\n");
        System.out.println(teacher.getFirst() + " " + teacher.getLast());
        System.out.println("ID#: " + teacher.getID() + "\n");
        System.out.print("1. Enter Student\n2. Search Student\n3. Remove Student\n4. List All Student\n\nAction: "); a = scan.nextInt(); scan.nextLine();
        }catch(InputMismatchException e){
            scan.nextLine(); menu(teacher);
        }
        }

        teacherPath(a, teacher);
    }

    public void teacherPath(int n, Teach teacher){
        switch(n){
            case 1: direct.enterNames(teacher.getID()); break;
        }

    }

    public void message(Teach teacher){
        System.out.println("\n       **A MESSAGE FROM PRINCIPAL LIL B THE BASEDGOD**\n");
        System.out.println("Congratulations " + ((teacher.getGenderString() == "Male") ? "Mr. " + teacher.getLast(): "Mrs. " + teacher.getLast()));
        System.out.println("\nWelcome to BASED University, " + teacher.getFirst() + " " + teacher.getLast() + ", your account has been created.");
        System.out.println("You now have the ability to add upcoming or edit student's information that are enrolled in your course");
        System.out.println("Have a wonderful semester\n\nBASEDGOD OUT!\n\n");
        System.out.println("(Press Anything To Continue)\n\n"); scan.nextLine();

        direct.searchFiles(teacher.getID());
    }

}