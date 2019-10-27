import java.util.Scanner;
import java.util.ArrayList;

public class Student {

    static Scanner scan = new Scanner(System.in);

    String fname;
    String lname;
    int id;
    String dob;
    int age;
    String year;
    char gender;
    String gender2;
    boolean status;
    String status2;
    String hobby;

    

    public Student(String first, String last, int s_id, String birth, int s_age, 
    String grade, char gen, String gen2, boolean stats, String stats2, String hobs){

        fname = first;  
        lname = last;
        id = s_id;
        dob = birth;
        age = s_age;
        year = grade;
        gender = gen;
        gender2 = gen2;
        status = stats;
        status2 = stats2;
        hobby = hobs;
    }

    static ArrayList <Student> stud = new ArrayList<Student>();

    public String toString(){
        return "\n\nFirst Name: " + fname + "\n" +
                "Last Name: " + lname + "\n" +
                "Student #ID: " + id + "\n" +
                "DOB: " + dob + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender2 + "\n" +
                "Grade: " + year + "\n" +
                "Status: " +  status2 + "\n" +
                "Hobby: " + hobby + "\n" +
                "***************************\n\n";
    }

    public String printAll(){
        return id + " " + fname  + " " + lname + " " + year + " " + status2 + "\n";
    }


    public static void main(String[] args){
        Student student = null;
       System.out.print("\nEnter Number Of Students: "); int num = scan.nextInt(); scan.nextLine();
       for(int i = 0;  i < num; i++){
        System.out.print("Enter First Name: "); String fname = scan.nextLine();
        System.out.print("Enter Last Name: "); String lname = scan.nextLine();
        System.out.print("Enter Student ID#(####): "); int id = scan.nextInt(); scan.nextLine();
        System.out.print("Enter Date Of Birth(mm/dd/yyyy): "); String dob = scan.nextLine();
        System.out.print("Enter Age: "); int age = scan.nextInt(); scan.nextLine();
        System.out.print("Enter School Year: "); String year = scan.nextLine();
        System.out.print("Enter Gender(M/F): "); char gen = scan.next().charAt(0); scan.nextLine();
        String gen2=  (gen == 'M' || gen == 'm')? "Male" : "Female";
        System.out.print("Enter Status(Pass/Fail): "); String stats2 = scan.nextLine();
        boolean stats = (stats2 == "Pass") ? true: false;
        System.out.print("Enter Student's Hobby: "); String hobby = scan.nextLine(); System.out.println("\n");
        
        student = new Student(fname, lname, id, dob, age, year, gen, gen2, stats, stats2, hobby);
        stud.add(student);

       }

      for(Student tu : stud){
        System.out.println(tu);

      }
          
      
    }


}