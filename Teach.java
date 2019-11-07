
//Teacher Information Gathered Here.
public class Teach{

    // Made Private Because At This Point I Knew How Encapsulation Worked//

    private String f_name; //Teacher First Name.
    private String l_name; //Teajcer Last Name.
    private boolean gen1; //Teacher Gender Toggle/Flag (Might Be The Most Useless Tool In This Program Looking Back).
    private String gen2; //Teacher Gender String Format
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
    public void setGender(boolean gen1){
        this.gen1 = gen1;
    }
    public boolean getGender(){
            return gen1;
    }

    //Modify first name information
    public void setGenderString(String gend){
        this.gen2 = gend;
    }
    public String getGenderString(){
            return gen2;
    }

    //Modify ID information
    public void setID(int id){
        this.ID = id;
    }
    public int getID(){
            return ID;
    }

    public String toString(){
        return getFirst() + " " + getLast() + " " + getID() + " " + getGenderString() + " " + getGender();
    }
    

    
}