import java.util.ArrayList;


//Student Information Gathered Here.
public class Student {

    /* Made Information Public Due To The Fact -
    - I Did Not Know How Encapsulation Worked At The Time. */

    public String fname; //Student first name.
    public String lname; //Student last name.
    public int id; //Student ID Number.
    public String dob; //Student Date Of Birth.
    public int age; //Student Age (Honestly Uneccessary But Looks Good).
    public String year; //Student School Year.
    public String gender; //Student Gender(Male or Female).
    public String status; //Student Status(Passing or Failing).
    public String description; //Student Description (Uneccessary But Adds Personality).

    public Student(){}

    //Constructor For Student Object
    public Student(String sf_name, String sl_name, int s_id, String s_dob, int s_age,
    String s_year, String s_gender, String s_status, String s_descr){
        this.fname = sf_name;
        this.lname = sl_name;
        this.id = s_id;
        this.dob = s_dob;
        this.age = s_age;
        this.year = s_year;
        this.gender = s_gender;
        this.status = s_status;
        this.description = s_descr;
    }

    

    //Display Detailed Student Information.
    public String toString(){
        return "First Name: " + fname + "\n" +
                "Last Name: " + lname + "\n" +
                "Student #ID: " + id + "\n" +
                "DOB: " + dob + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Grade: " + year + "\n" +
                "Status: " +  status + "\n" +
                "Description: " + description + "\n";
    }

    public void header(){
        String n = String.format("%7s %9s %15s %15s %10s %10s %10s", "ID", "|", "Name", "|", "Year", "|", "Status");
        String lines = "__________________________________________________________________________________________";
        System.out.println(n + "\n" + lines);
    }

    //Details Quick Student Information For The List Of Students
    public String printAll(){
        return String.format("%8s %8s %20s %10s %13s %7s %9s",   id, "|", fname.concat(" " + lname), "|", year, "|", status);
    }     
      
}

