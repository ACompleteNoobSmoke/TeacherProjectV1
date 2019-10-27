import java.util.Scanner;
import java.util.ArrayList;

public class Student {

    static Scanner scan = new Scanner(System.in);

    private String fname;
    private String lname;
    private int id;
    private String dob;
    private int age;
    private String year;
    private String gender;
    private String status;
    private String hobby;

    public Student(String sf_name, String sl_name, int s_id, String s_dob, int s_age,
    String s_year, String s_gender, String s_status, String s_hobby){
        this.fname = sf_name;
        this.lname = sl_name;
        this.id = s_id;
        this.dob = s_dob;
        this.age = s_age;
        this.year = s_year;
        this.gender = s_gender;
        this.status = s_status;
        this.hobby = s_hobby;
    }

    

    static ArrayList <Student> stud = new ArrayList<Student>();

    public String toString(){
        return "\n\nFirst Name: " + fname + "\n" +
                "Last Name: " + lname + "\n" +
                "Student #ID: " + id + "\n" +
                "DOB: " + dob + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Grade: " + year + "\n" +
                "Status: " +  status + "\n" +
                "Hobby: " + hobby + "\n" +
                "***************************\n\n";
    }

    public String printAll(){
        return id + " " + fname  + " " + lname + " " + year + " " + status2 + "\n";
    }

        public static void studentInfo(){
    
            Teach teacher = new Teach();
            teacher.setFirst("Uyi"); teacher.setLast("Omo");
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

            while(s_id2.length() < 4 || s_id2.length() > 4){
                System.out.println("(Please ID# Should Start With Number Greater Than 0\n");
                System.out.print("Enter Student ID#: "); s_id = scan.nextInt(); scan.nextLine();
                s_id2 = Integer.toString(s_id);
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

            Student student = new  Student(sf_name, sl_name, s_id, s_dob, s_age, 
            s_year, s_gender, s_status, s_hobby);

            System.out.println(student.toString());

      }

      public static void main(String[] args) {
         studentInfo();
      }
      
          
      
    }

