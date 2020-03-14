import model.Teach;

public class Course{

    static Directory d = new Directory();
    static Methods m = new Methods();
  
    public static void main(String[] args) {
    	
    	Teach newTeacher = null;
    	
    	do{
    		newTeacher = m.TeacherPath(m.MainMenu());
    		if(newTeacher != null) {
    		m.TeacherMenu(newTeacher);
    		}
    	}while(newTeacher == null);   
    }
    
}