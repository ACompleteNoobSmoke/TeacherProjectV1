package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Student Information Model Gathered Here.
@Entity
@Table(name="Students")
public class Student {

  
	@Column(name="Teacher_ID")
	private int teacherid;
	
	@Column(name="Student_FirstName")
    private String fname; //Student first name.
	
	@Column(name="Student_LastName")
    private String lname; //Student last name.
	
	@Column(name="Student_FullName")
    private String fullname; //Student full name.
	
    @Id
    @Column(name="Student_ID")
    private int id; //Student ID Number.
    
   // @Column(name="Unique_ID")
   // private int uniqueID;
    
    @Column(name="Student_DOB")
    private String dob; //Student Date Of Birth.
    
    @Column(name="Student_Age")
    private int age; //Student Age (Honestly Unnecessary But Looks Good).
    
    @Column(name="Student_Year")
    private String year; //Student School Year.
    
    @Column(name="Student_Gender")
    private String gender; //Student Gender(Male or Female).
    
    @Column(name="Student_Status")
    private String status; //Student Status(Passing or Failing).
    
    @Column(name="Student_Hobby")
    private String hobby; //Student Hobby (Unnecessary But Adds Personality).
    
    
    //Default Constructor For JPA
    public Student() {}

  
    //Constructor For Student Object
    public Student(int teacherid,String sf_name, String sl_name, int s_id, String s_dob, int s_age,
    String s_year, String s_gender, String s_status, String s_hobby){
        this.teacherid = teacherid;
    	this.fname = sf_name;
        this.lname = sl_name;
        this.fullname = sf_name.concat(" " + sl_name);
        this.id = s_id;
        this.dob = s_dob;
        this.age = s_age;
        this.year = s_year;
        this.gender = s_gender;
        this.status = s_status;
        this.hobby = s_hobby;
    }

    

    public int getTeacherid() {
		return teacherid;
	}
    
    public void setTeacherID(int teacherID) {
    	this.teacherid = teacherID;
    }


	public String getFname() {
		return fname;
	}


	public String getLname() {
		return lname;
	}


	public String getFullname() {
		return fullname;
	}
	
	//public void setUnique(int teacherID, int studentID) {
		
	//}


	public int getId() {
		return id;
	}
	
	public void setID(int newID) {
		this.id = newID;
	}


	public String getYear() {
		return year;
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

