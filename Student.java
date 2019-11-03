import java.util.Scanner;
import java.util.ArrayList;

public class Student {

    static Scanner scan = new Scanner(System.in);

    public String fname;
    public String lname;
    public int id;
    public String dob;
    public int age;
    public String year;
    public String gender;
    public String status;
    public String hobby;


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

    


    public String toString(){
        return "First Name: " + fname + "\n" +
                "Last Name: " + lname + "\n" +
                "Student #ID: " + id + "\n" +
                "DOB: " + dob + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Grade: " + year + "\n" +
                "Status: " +  status + "\n" +
                "Hobby: " + hobby + "\n";
    }

    public String printAll(){
        return id + " " + fname  + " " + lname + " " + year + " " + status;
    }

        
     
      
          
      
    }

