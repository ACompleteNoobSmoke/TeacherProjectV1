import model.Teach;

public class Course{

    static Directory d = new Directory();
    static Methods m = new Methods();
  
    public static void main(String[] args) {
        Teach newTeacher = m.Register();
        d.addTeacher(newTeacher);
    }
    
}