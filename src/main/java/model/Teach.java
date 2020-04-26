package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Teacher Information Model Gathered Here.
@Entity
@Table(name="Teachers")
public class Teach{

    // Made Private Because At This Point I Knew How Encapsulation Worked//

	@Column(name="Teacher_FirstName")
    private String FirstName; //Teacher First Name.
	
	@Column(name="Teacher_LastName")
    private String LastName; //Teacher Last Name.
	
	@Column(name="Teacher_Gender")
    private String Gender; //Teacher Gender
	
	
    @Id
    @Column(name="Teacher_ID")
    private int ID; //Teacher ID Number
    
    @Column(name="Teacher_Password")
    private String Password; //Teacher Password
    
    public Teach() {}
    
    public Teach(int ID, String Password, String FirstName, String LastName, String Gender) {
    	this.ID = ID;
    	this.Password = Password;
    	this.FirstName = FirstName;
    	this.LastName = LastName;
    	this.Gender = Gender;
    }


    //Modify first name information
    public void setFirst(String fname) {
    	this.FirstName = fname;
    }
    public String getFirst() {
    	return FirstName;
    }

    //Modify last name information
    public void setLast(String lname){
        this.LastName = lname;
    }
    public String getLast(){
            return LastName;
    }

    //Modify gender information
    public void setGender(String gender) {
    	this.Gender = gender;
    }
    public String getGender() {
    	return Gender;
    }
 
    //Modify ID information
    public void setID(int id){
        this.ID = id;
    }
    public int getID(){
            return ID;
    }
    
    //Modify password information
    public void setPassword(String password) {
    	this.Password = password;
    }
    public String getPassword() {
    	return Password;
    }

    public String toString(){
        return getFirst() + " " + getLast() + " " + getID() + " "  + getGender();
    }
    

    
}