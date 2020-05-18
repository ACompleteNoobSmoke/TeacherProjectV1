import methods.Methods;
import model.Teach;
import repo.Directory;

public class Course{

    static Directory d = new Directory();
    static Methods m = new Methods();
  
    public static void main(String[] args) {
    	Teach newTeacher = null;
    	while(newTeacher == null) {
    		newTeacher = m.teacherPath();
    		if(newTeacher != null) {
    		newTeacher = m.teacherOptions(newTeacher);
    		}
    	}
    } 
}