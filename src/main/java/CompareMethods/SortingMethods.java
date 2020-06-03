package CompareMethods;

import java.util.ArrayList;
import java.util.Collections;
import model.Student;
import model.Teach;
import CompareMethods.CompareStudentByStudentID;
import CompareMethods.CompareStudentByTeacherID;
import CompareMethods.CompareTeacherByTeacherID;

public class SortingMethods {
	
	public static ArrayList<Student> sortStudentForPrincipal(ArrayList<Student> sortThis){
		Collections.sort(sortThis, new CompareStudentByTeacherID().thenComparing(new CompareStudentByStudentID()));
		return sortThis;
	}
	
	public static ArrayList<Student> sortStudentByID(ArrayList<Student> sortThis){
		Collections.sort(sortThis, new CompareStudentByStudentID());
		return sortThis;
	}
	
	public static ArrayList<Teach> sortTeacherByID(ArrayList<Teach> sortThis){
		Collections.sort(sortThis, new CompareTeacherByTeacherID());
		return sortThis;
	}
}
