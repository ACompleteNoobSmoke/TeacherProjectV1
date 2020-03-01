package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Teacher Information Model Gathered Here.
@Entity
@Table(name="Teachers")
public class Teach{

    // Made Private Because At This Point I Knew How Encapsulation Worked//

    private String FirstName; //Teacher First Name.
    private String LastName; //Teacher Last Name.
    private String Gender; //Teacher Gender
    @Id
    private int ID; //Teacher ID Number
    private String Password; //Teacher Password


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