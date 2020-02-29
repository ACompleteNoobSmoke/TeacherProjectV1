package model;

//Teacher Information Model Gathered Here.
public class Teach{

    // Made Private Because At This Point I Knew How Encapsulation Worked//

    private String f_name; //Teacher First Name.
    private String l_name; //Teacher Last Name.
    private String gender; //Teacher Gender
    private int ID; //Teacher ID Number


    //Modify first name information
    public void setFirst(String name){
        this.f_name = name;
    }
    public String getFirst(){
            return f_name;
    }

    //Modify last name information
    public void setLast(String name){
        this.l_name = name;
    }
    public String getLast(){
            return l_name;
    }

    //Modify gender information
    public void setGender(String gender) {
    	this.gender = gender;
    }
    public String getGender() {
    	return gender;
    }
 

    //Modify ID information
    public void setID(int id){
        this.ID = id;
    }
    public int getID(){
            return ID;
    }

    public String toString(){
        return getFirst() + " " + getLast() + " " + getID() + " "  + getGender();
    }
    

    
}