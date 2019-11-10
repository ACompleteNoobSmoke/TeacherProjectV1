import java.io.IOException;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;
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

    //Main Menu Page -- GOOD//
    public void mainmenu(){
        boolean repeat = true;
        direct.createFolder(); //Initially creates folder to save information.

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
            mainmenu();
        }

       choice(i);
    }

    }

    //Switch case of options -- GOOD//
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
//#endregion




    // Method To Register New Teacher Information -- GOOD//
    public void regTeacher() {
        boolean sec = false; //Toggle For Gender Question.
        boolean gen2 = false; //For Teacher Gender Flag (Kind Of Unnecessary).
        String first = ""; //For Teacher First Name.
        String last = ""; //For Teacher Last Name.
        char gen; //Same As Gen2.
        int id = 0; //For Teacher ID Number.
        String id2 = ""; //Changes ID Number Into A String.
        boolean check = false; //Use To Flag If Teacher ID Exists Already.

    
        System.out.println("\n\n****Teacher Registration****\n");

        //Gets Teacher First Name.
        while (first.equals("")) {
            System.out.print("Please Enter First Name: ");
            first = scan.nextLine();
        }

        //Gets Teacher Last Name.
        while (last.equals("")) {
            System.out.print("Please Enter Last Name: ");
            last = scan.nextLine();
        }

        //Gets Teacher Gender.
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

        
        //Gets Teacher ID Number
        while ((id2.length()< 4 || id2.length() > 4) || check == false) {
            try{
            System.out.println("**(Note: First Digit Has To Be Greater Than 0**)");
            System.out.print("Please Enter ID Number(####): ");
            id = scan.nextInt();
            scan.nextLine();
        
            id2 = Integer.toString(id);
            if(id2.length() == 4){
            String fileSearch = id2 + ".txt";
             check = direct.searchID(fileSearch); //Searches folder to see if ID has been taken
            }   
        }catch(InputMismatchException E){
            scan.nextLine();
            check = false;
        }         
        
        }
    

        //Option To Save Teacher Information Or Cancel & Get Sent Back To Menu
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
            case 1:   //Saves Teacher Information
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
                    direct.createFile(teacher); //Creates A File In The Folder To Store Teacher Information
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


    //Teacher Login -- GOOD//
    public static void login() {

        int id = 0; 
        System.out.println("\n\n****LOGIN****\n");
        while(id <1000 || id > 9999){
            try{
        System.out.print("Enter ID(####): "); 
        id = scan.nextInt(); scan.nextLine();
        direct.searchFiles(id); //Searches Folder To See If Teacher File Exists.
        }catch(InputMismatchException E){
        scan.nextLine();
        login();
        }
        }

        
    }

    

    //Teacher Menu -- GOOD//
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


    //Switch Cases From Teacher Menu That Lead To Different Paths
    public void teacherPath(int n, Teach teacher){
        switch(n){
            case 1: //Path To Enter Student Information That Will Be Saved In File
            System.out.println("\n\n");
            direct.enterNames(teacher, studentInfo(teacher)); menu(teacher); break;

            case 2: //Path To Search For Student Based On ID Entered 
            System.out.println("\n\n");
            System.out.println(direct.searchStudent(teacher.getID(), studentid(teacher.getID()))); //Display Student Information
            System.out.println("\nPress Anything To Exit\n"); scan.nextLine(); menu(teacher);
            break;

            case 3: //Path To Search For Student For Deletion
            int choice = 0;
            System.out.println("\n\n");
            int sid = studentid(teacher.getID());
            String check = direct.searchStudent(teacher.getID(), sid); //Returns Student Information
            System.out.println(check); //Prints Student Information
            if(check.equals("STUDENT INFORMATION DOES NOT EXIST!")){
                menu(teacher);//If Student Information Does Not Exist Returns Back To Menu
                }
            while(choice != 1 && choice != 2){
                try{
            System.out.print("\n**Option**\n1. Delete Student Information\n2. Cancel\n\nAction: ");
            choice = scan.nextInt(); scan.nextLine();
                if(choice == 1){
                    direct.searchStudentForDeletion(teacher.getID(), sid); //Searches For Student To Delete
                    menu(teacher);
                }else if(choice == 2){
                    menu(teacher);
                    break;
                 }
                }catch(InputMismatchException E){
                    scan.nextLine();
                }
             }
            break;

            case 4: //Display List Of Students Saved In Teacher File
                    System.out.println("***Student List***\n");
                    System.out.println("Professor: " + teacher.getFirst().concat(" " + teacher.getLast()));
                    System.out.println("ID: " + teacher.getID());  System.out.println();
                    
                   ArrayList<Student> wholeClass = direct.getWholeClass(teacher.getID()); //Gathers Array Of Students From File
                    if(wholeClass.isEmpty()){
                        System.out.println("No Student Enrolled!\n"); //If File Is Empty Display This
                    }else{
                        Student print = new Student();
                        print.header();
                       for(Student p : wholeClass){
                           System.out.println(p.printAll()); //If Not Empty Then Display List
                       }
                    }

                    System.out.println("\nPress Anything To Continue"); scan.nextLine(); menu(teacher); break;

             case 5: //Logging Out 
                    System.out.println("\nGoodbye " + (teacher.getGenderString().equalsIgnoreCase("Male") ? "Mr. " : "Mrs. ") + teacher.getLast() + "\n");
                     mainmenu(); break;
            
            default: System.out.println("\nAction Not Recognized\n"); menu(teacher);
        }
    }

    //Registering New Student
    public Student studentInfo(Teach teacher){
    
         
        String sf_name = "";    //Student First Name
        String sl_name = "";    //Student Last Name
        int s_id = 0;           //Student ID Number
        String s_id2 = "";      //Student ID In String(Security Reasons)
        String s_dob = "";      //Student Date Of Birth
        int s_age = 0;          //Student Age
        String s_year = "";     //Student Year Is School
        String s_gender = "";   //Student Gender
        String s_status = "";   //Student Status(Passing/Failing)
        String s_hobby = "";    //Student Hobby
        boolean check = false;  //Check If Student Already Exist In File
        dateFormat.setLenient(false);
        boolean checkDate = false;

        System.out.println("***Enter Student Information***\n");
        System.out.println("Professor Name: " + teacher.getFirst().concat(" " + teacher.getLast()));
        System.out.println("ID#: " + teacher.getID() + "\n");
        
        //Assign Student First Name
        while(sf_name.equals("")){
            System.out.print("Enter Student First Name: ");
            sf_name = scan.nextLine();
        }

        //Assign Student Last Name
        while(sl_name.equals("")){
            System.out.print("Enter Student Last Name: ");
            sl_name = scan.nextLine();
        }

        //Assign Student ID Number
        while((s_id2.length() < 4 || s_id2.length() > 4) || !check){
            try{
            System.out.println("(Please ID# Should Start With Number Greater Than 0\n");
            System.out.print("Enter Student ID#: "); s_id = scan.nextInt(); scan.nextLine();
            s_id2 = Integer.toString(s_id);
            check = direct.searchStuID(teacher.getID(), s_id);
            }catch(InputMismatchException E){
                scan.nextLine();
                s_id2 = " ";
            }
        }

        //Assign Student DOB
        while(!checkDate){
            try{
            System.out.print("Enter Student DOB(mm/dd/yyyy): "); s_dob = scan.nextLine();
            dateFormat.parse(s_dob); //Parses Date Entered To Ensure It Is The Correct Format
            checkDate = true;
            }catch(ParseException E){

            }
        }

        while(s_age <= 0){
            try{
            System.out.print("Enter Student Age: "); 
            s_age = scan.nextInt(); scan.nextLine();
            }catch(InputMismatchException E){
                scan.nextLine();
                s_age = 0;
            }
        }

        while((!s_gender.startsWith("M") && !s_gender.startsWith("m")) && 
        (!s_gender.startsWith("F") && !s_gender.startsWith("f"))){
            System.out.print("Enter Student Gender(M/F): "); s_gender = scan.nextLine();
            if(s_gender.startsWith("M") || s_gender.startsWith("m")){
                s_gender = "Male";
            }else if(s_gender.startsWith("F") || s_gender.startsWith("f")){
                s_gender = "Female";
        }
    }

        while((!s_year.equalsIgnoreCase("Freshman")&& !s_year.equalsIgnoreCase("Sophomore")) &&
        (!s_year.equalsIgnoreCase("Junior") && !s_year.equalsIgnoreCase("Senior")) && !s_year.equalsIgnoreCase("Graduate")){
            System.out.println("(School Year Options: Freshman, Sophomore, Junior, Senior & Graduate)\n");
            System.out.print("Enter Student Year: "); s_year = scan.nextLine();
            if(s_year.equalsIgnoreCase("freshman")){
                s_year = "Freshman";
                break;
            }else if(s_year.equalsIgnoreCase("sophomore")){
                s_year = "Sophomore";
                break;
            }else if(s_year.equalsIgnoreCase("junior")){
                s_year = "Junior";
                break;
            }else if(s_year.equalsIgnoreCase("senior")){
                s_year = "Senior";
                break;
            }else if(s_year.equalsIgnoreCase("graduate")){
                s_year = "Graduate";
                break;
            }

        }

        while(!s_status.equalsIgnoreCase("Pass") && !s_status.equalsIgnoreCase("Fail")){
            System.out.print("Enter Student Status(Pass/Fail): "); s_status = scan.nextLine();
            if(s_status.equalsIgnoreCase("pass")){
                s_status = "Pass";
                break;
            }else if(s_status.equalsIgnoreCase("fail")){
                s_status = "Fail";
                break;
            }
        }

        while(s_hobby.equals("") || s_hobby.contains(" ")){
            System.out.print("Describe Student In One Word: "); s_hobby = scan.nextLine();
            
        }
        int op = 0;
        while(op != 1 && op != 2){
            try{
        System.out.print("\n\nOptions:\n1. Save " + sf_name + " Information\n2. Cancel\n\nAction: "); op = scan.nextInt(); scan.nextLine();
        
        
        if(op == 2){ 
            Menu menu = new Menu();
            menu.menu(teacher);   
        }
        
        
    }catch(InputMismatchException E){
        scan.nextLine();
        op = 0;
    }
    
}
        
Student student = new  Student(sf_name, sl_name, s_id, s_dob, s_age, 
s_year, s_gender, s_status, s_hobby);
return student;

  }

  //Method to ensure that student id is not same as the professor id
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
    

    //Method that prints when a new teacher is saved to folders
    public void message(Teach teacher){
        System.out.println("\n       **A MESSAGE FROM PRINCIPAL LIL B THE BASEDGOD**\n");
        System.out.println("Congratulations " + ((teacher.getGenderString() == "Male") ? "Mr. " + teacher.getLast(): "Mrs. " + teacher.getLast()));
        System.out.println("\nWelcome to BASED University, " + teacher.getFirst() + " " + teacher.getLast() + ", your account has been created.");
        System.out.println("You now have the ability to add upcoming or edit student's information that are enrolled in your course");
        System.out.println("Have a wonderful semester\n\nBASEDGOD OUT!\n\n");
        System.out.println("(Press Anything To Continue)\n\n"); scan.nextLine();

        direct.searchFiles(teacher.getID());
    }

    public void introduction(){
        System.out.println("                             ***Project 1: Teacher Course***\n");
        System.out.println("Name: Omoruyi Omofonmwan\nAge: 26\nBackground: Self-Taught Java Developer\n\n");
        System.out.println("Summary: Hello, This is the first project in the seris of projects I aim to develop using Java.");
        System.out.println("I used different techniques in this project such as Encapsulation, String Methods,\n ArrayList, Object Creation and Writing & Reading From Files.");
        System.out.println("\nInstructions: When it says \"Action: \" please enter number associated with the action that you wish to take.");
        System.out.println("Any feedback would greatly appreciated. Thank You\n\n\n**Press Anything To Continue**"); scan.nextLine();
    }

}