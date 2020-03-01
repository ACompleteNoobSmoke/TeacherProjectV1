package model;

import javax.persistence.Entity;
import javax.persistence.Id;

//Student Information Model Gathered Here.
@Entity
public class Student {

  

    private String fname; //Student first name.
    private String lname; //Student last name.
    @Id
    private int id; //Student ID Number.
    private String dob; //Student Date Of Birth.
    private int age; //Student Age (Honestly Unnecessary But Looks Good).
    private String year; //Student School Year.
    private String gender; //Student Gender(Male or Female).
    private String status; //Student Status(Passing or Failing).
    private String hobby; //Student Hobby (Unnecessary But Adds Personality).

    

    //Constructor For Student Object
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
                "Hobby: " + hobby + "\n";
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

