import java.io.IOException;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;
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
    static Date date = new Date();
    
   
    //Clear The Screen
    public static void clearScreen() {  
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch(Exception E){
            System.out.println(E);
        }
     }  

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

            case 3: System.out.println("Program Closed!"); System.exit(0);

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
        boolean check = false;

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

        while ((id2.length()< 4 || id2.length() > 4) || check == false) {
            System.out.println("**(Note: First Digit Has To Be Greater Than 0**)");
            System.out.print("Please Enter ID Number(####): ");
            id = scan.nextInt();
            scan.nextLine();
            id2 = Integer.toString(id);
            if(id2.length() == 4){
            String fileSearch = id2 + ".txt";
             check = direct.searchID(fileSearch);
            }            
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
         
        
        while(a <1 || a > 5){
        try{
        System.out.println("*****Professor Menu*****\n");
        System.out.println(teacher.getFirst() + " " + teacher.getLast());
        System.out.println("ID#: " + teacher.getID() + "\n");
        System.out.print("1. Enter Student\n2. Search Student\n3. Remove Student\n4. List All Student\n5. Sign Out\n\nAction: "); a = scan.nextInt(); scan.nextLine();
        }catch(InputMismatchException e){
            scan.nextLine(); menu(teacher);
        }
        }

        teacherPath(a, teacher);
    }

    public void teacherPath(int n, Teach teacher){
        switch(n){
            case 1: System.out.println("\n\n");
            direct.enterNames(teacher, studentInfo(teacher)); menu(teacher); break;

            case 2: System.out.println("\n\n");
            System.out.println(direct.searchStudent(teacher.getID(), studentid(teacher.getID())));
            System.out.println("\nPress Anything To Exit\n"); scan.nextLine(); menu(teacher);
            break;

            case 3: int choice = 0;
            System.out.println("\n\n");
            int sid = studentid(teacher.getID());
            String check = direct.searchStudent(teacher.getID(), sid);
            System.out.println(check);
            if(check.equals("STUDENT INFORMATION DOES NOT EXIST!")){
                clearScreen();
                menu(teacher);
                }
            while(choice != 1 && choice != 2){
            System.out.print("\n**Option**\n1. Delete Student Information\n2. Cancel\n\nAction: ");
            choice = scan.nextInt(); scan.nextLine();
                if(choice == 1){
                    direct.searchStudentForDeletion(teacher.getID(), sid);
                }else if(choice == 2){
                    menu(teacher);
                    break;
                 }
             }
            break;

            case 4: System.out.println("***Student List***\n");
                    System.out.println("Professor: " + teacher.getFirst().concat(" " + teacher.getLast()));
                    System.out.println("ID: " + teacher.getID());  System.out.println();
                    
                   ArrayList<Student> wholeClass = direct.getWholeClass(teacher.getID());
                    if(wholeClass.isEmpty()){
                        System.out.println("No Student Enrolled!\n");
                    }else{
                       for(Student p : wholeClass){
                           System.out.println(p.printAll());
                       }
                    }

                    System.out.println("\nPress Anything To Continue"); scan.nextLine(); menu(teacher); break;

             case 5: System.out.println("\nGoodbye " + (teacher.getGenderString().equalsIgnoreCase("Male") ? "Mr. " : "Mrs. ") + teacher.getLast() + "\n");
                     mainmenu(); break;
            
            default: System.out.println("\nAction Not Recognized\n"); menu(teacher);
        }
    }

    public Student studentInfo(Teach teacher){
    
        String sf_name = "";
        String sl_name = "";
        int s_id = 0;
        String s_id2 = "";
        String s_dob = "";
        int s_age = 0;
        String s_year = "";
        String s_gender = "";
        String s_status = "";
        String s_hobby = "";
        boolean check = false;

        System.out.println("***Enter Student Information***\n");
        System.out.println("Professor Name: " + teacher.getFirst().concat(" " + teacher.getLast()));
        System.out.println("ID#: " + teacher.getID() + "\n");
        
        while(sf_name.equals("")){
            System.out.print("Enter Student First Name: ");
            sf_name = scan.nextLine();
        }

        while(sl_name.equals("")){
            System.out.print("Enter Student Last Name: ");
            sl_name = scan.nextLine();
        }

        while((s_id2.length() < 4 || s_id2.length() > 4) || !check){
            System.out.println("(Please ID# Should Start With Number Greater Than 0\n");
            System.out.print("Enter Student ID#: "); s_id = scan.nextInt(); scan.nextLine();
            s_id2 = Integer.toString(s_id);
            check = direct.searchStuID(teacher.getID(), s_id);
        }

        while(s_dob == "" ){
            System.out.print("Enter Student DOB(dd/mm/yyyy): "); s_dob = scan.nextLine();
        }

        while(s_age <= 0){
            System.out.print("Enter Student Age: "); 
            s_age = scan.nextInt(); scan.nextLine();
        }

        while(!s_gender.startsWith("M") && !s_gender.startsWith("F")){
            System.out.print("Enter Student Gender(M/F): "); s_gender = scan.nextLine();
            s_gender = s_gender.toUpperCase();
            
        }

        while((!s_year.equalsIgnoreCase("Freshman")&& !s_year.equalsIgnoreCase("Sophomore")) &&
        (!s_year.equalsIgnoreCase("Junior") && !s_year.equalsIgnoreCase("Senior")) && !s_year.equalsIgnoreCase("Graduate")){
            System.out.println("(School Year Options: Freshman, Sophomore, Junior, Senior & Graduate)\n");
            System.out.print("Enter Student Year: "); s_year = scan.nextLine();
        }

        while(!s_status.equalsIgnoreCase("Pass") && !s_status.equalsIgnoreCase("Fail")){
            System.out.print("Enter Student Status(Pass/Fail): "); s_status = scan.nextLine();
        }

        while(s_hobby.equals("") || s_hobby.length()>140){
            System.out.print("Enter Student Hobby: "); s_hobby = scan.nextLine();
            
        }
        int op = 0;
        while(op != 1 && op != 2){
        System.out.print("\n\nOptions:\n1. Save " + sf_name + " Information\n2. Cancel\n\nAction: "); op = scan.nextInt(); scan.nextLine();
        }
        
        if(op == 1){
        Student student = new  Student(sf_name, sl_name, s_id, s_dob, s_age, 
        s_year, s_gender, s_status, s_hobby);
        return student;
        }else{
            Menu menu = new Menu();
            menu.menu(teacher);
            return null;
        }
        

  }

    public int studentid(int t_id){
        int idnum = 0;
        String idstr = "";
        boolean check = false;
        try{
        System.out.println("\n***Search Student***");
        while((idstr.length() < 4 || idstr.length() > 4) || !check){
        System.out.print("\nEnter ID Number: "); idnum = scan.nextInt(); scan.nextLine();
        if(idnum == t_id){
            System.out.println("Professor ID Currently In Usage\n");
        }else{
            check = true;
        }
        idstr = Integer.toString(idnum);
        }
      
      
    }catch(InputMismatchException e){
        scan.nextLine();
        check = false;
        studentid(t_id);
    }
    return idnum;
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