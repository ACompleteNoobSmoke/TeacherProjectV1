package CompareMethods;

import java.util.Comparator;

import model.Student;

public class CompareStudentByTeacherID implements Comparator<Student>{
	public int compare(Student s1, Student s2) {
		return s1.getTeacherid() - s2.getTeacherid();
	}
}
