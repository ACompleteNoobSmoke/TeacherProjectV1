import methods.Methods;
import model.Teach;
import repo.Directory;

public class Course{

    static Directory d = new Directory();
    static Methods m = new Methods();
  
    public static void main(String[] args) {
    	int choices;
    	Teach newTeacher = null;
    	while(true) {
    		choices = m.mainMenu();
    		newTeacher = m.teacherPath(choices);
    		m.teacherMenu(newTeacher);
    	}
    } 
}