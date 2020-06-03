package CompareMethods;

import java.util.Comparator;

import model.Teach;

public class CompareTeacherByTeacherID implements Comparator<Teach>{
	public int compare(Teach t1, Teach t2) {
		return t1.getID() - t2.getID();
	}
}
