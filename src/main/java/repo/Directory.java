package repo;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import CompareMethods.SortingMethods;
import model.Student;
import model.Teach;



public class Directory{
	
	private static EntityManagerFactory EFM = Persistence.createEntityManagerFactory("pu");
	
	public void addTeacher(Teach newTeacher) {
		EntityManager em = EFM.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			
			em.persist(newTeacher);
			et.commit();
		}catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}finally {
			em.close();
		}
	}
 
	public static boolean checkTeacherID(int id) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT t FROM Teach t WHERE t.ID = :ID";
		
		TypedQuery<Teach> tq = em.createQuery(query, Teach.class);
		tq.setParameter("ID", id);
		Teach teacher = null;
		
		try {
			teacher = tq.getSingleResult();
		}catch(NoResultException ex) {
			
			return false;
		}finally {
			em.close();
		}
		
		return teacher != null;
	}
	
	public static Teach getTeacher(int id, String password) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT t FROM Teach t WHERE t.ID  = :ID AND t.Password = :PASSWORD";
		
		TypedQuery<Teach> tq = em.createQuery(query, Teach.class);
		tq.setParameter("ID", id);
		tq.setParameter("PASSWORD", password);
		Teach teacher = null;
		
		try {
			teacher = tq.getSingleResult();
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return teacher;
	}
	
	public static Teach searchTeacherID(int id) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT t FROM Teach t WHERE t.ID = :ID";
		
		TypedQuery<Teach> tq = em.createQuery(query, Teach.class);
		tq.setParameter("ID", id);
		Teach teacher = null;
		
		try {
			teacher = tq.getSingleResult();
		}catch(NoResultException ex) {
			
			return null;
		}finally {
			em.close();
		}
		
		return teacher;
	}
	
	public static Teach searchTeacherName(String firstName, String lastName) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT t FROM Teach t WHERE t.FirstName  = :firstName AND t.LastName = :lastName";
		
		TypedQuery<Teach> tq = em.createQuery(query, Teach.class);
		tq.setParameter("firstName", firstName);
		tq.setParameter("lastName", lastName);
		Teach teacher = null;
		
		try {
			teacher = tq.getSingleResult();
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return teacher;
	}
	
	public static ArrayList<Teach> getTeachers() {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT t FROM Teach t";
		
		TypedQuery<Teach> tq = em.createQuery(query, Teach.class);
		ArrayList<Teach> existingTeachers = null;
		
		try {
			existingTeachers = (ArrayList<Teach>) tq.getResultList();
			existingTeachers = SortingMethods.sortTeacherByID(existingTeachers);
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return existingTeachers;
	}
	
	public static ArrayList<Teach> getTeachersByGender(String gender) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT t FROM Teach t WHERE t.Gender = :GENDER";
		
		TypedQuery<Teach> tq = em.createQuery(query, Teach.class);
		tq.setParameter("GENDER", gender);
		ArrayList<Teach> genderTeachers = null;
		
		try {
			genderTeachers = (ArrayList<Teach>) tq.getResultList();
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return genderTeachers;
	}
	
	public static void addStudent(Student newStudent) {
		EntityManager em = EFM.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			
			em.persist(newStudent);
			et.commit();
		}catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public static boolean checkStudentID(int teacherID, int studentID ) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.id = :StudentID AND s.teacherid = :TeacherID";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("StudentID", studentID);
		tq.setParameter("TeacherID", teacherID);
		Student existingStudent = null;
		
		try {
			existingStudent = tq.getSingleResult();
		}catch(NoResultException ex) {
			
			return false;
		}finally {
			em.close();
		}
		
		return existingStudent != null;
	}
	
	public static Student getStudentByID(int teacherID, int studentID) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.id = :ID AND s.teacherid = :TeacherID";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("ID", studentID);
		tq.setParameter("TeacherID", teacherID);
		Student existingStudent = null;
		
		try {
			existingStudent = tq.getSingleResult();
		}catch(NoResultException ex) {
			return existingStudent;
		}finally {
			em.close();
		}
		
		return existingStudent;
	}

	public static Student getStudentByFullName(int teacherID, String studentName) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.fullname = :StudentName AND s.teacherid = :TeacherID";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("StudentName", studentName);
		tq.setParameter("TeacherID", teacherID);
		Student existingStudent = null;
		
		try {
			existingStudent = tq.getSingleResult();
		}catch(NoResultException ex) {
			return existingStudent;
		}finally {
			em.close();
		}
		
		return existingStudent;
	}
	
	public static ArrayList<Student> getStudentsBy(int teacherID, String gender, String schoolYear, String status) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.gender = :GENDER  OR s.year = :SCHOOLYEAR OR s.status = :STATUS AND s.teacherid = :TeacherID";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("GENDER", gender);
		tq.setParameter("SCHOOLYEAR", schoolYear);
		tq.setParameter("STATUS", status);
		tq.setParameter("TeacherID", teacherID);
		ArrayList<Student> existingStudents = null;
		
		try {
			existingStudents = (ArrayList<Student>) tq.getResultList();
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return existingStudents;
	}
	
	public static ArrayList<Student> getStudents(int teacherID) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE  s.teacherid = :TeacherID";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("TeacherID", teacherID);
		ArrayList<Student> existingStudents = null;
		
		try {
			existingStudents = (ArrayList<Student>) tq.getResultList();
			existingStudents = SortingMethods.sortStudentByID(existingStudents);
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return existingStudents;
	}
	
	public static ArrayList<Student> getStudentsByGender(String gender) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.gender = :gender";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("gender", gender);
		ArrayList<Student> existingStudents = null;
		
		try {
			existingStudents = (ArrayList<Student>) tq.getResultList();
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return existingStudents;
	}
	
	public static ArrayList<Student> getStudentsByYear(String year) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.year = :year";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("year", year);
		ArrayList<Student> existingStudents = null;
		
		try {
			existingStudents = (ArrayList<Student>) tq.getResultList();
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return existingStudents;
	}
	
	public static ArrayList<Student> getStudentsByStatus(String status) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.status = :status";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("status", status);
		ArrayList<Student> existingStudents = null;
		
		try {
			existingStudents = (ArrayList<Student>) tq.getResultList();
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return existingStudents;
	}
	
	public static ArrayList<Student> getAllStudents() {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		ArrayList<Student> existingStudents = null;
		
		try {
			existingStudents = (ArrayList<Student>) tq.getResultList();
		}catch(NoResultException ex) {
			return null;
		}finally {
			em.close();
		}
		
		return existingStudents;
	}
	
	public static void deleteStudent(int teacherID, int studentID) {
		EntityManager em = EFM.createEntityManager();
		EntityTransaction et = null;
		Query query = em.createNativeQuery("DELETE FROM Students WHERE Teacher_ID = " + teacherID + 
				" AND Student_ID = " + studentID);
		
		try {
			
			et = em.getTransaction();
			et.begin();
			query.executeUpdate(); 
			et.commit();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public static void deleteTeacher(int teacherID) {
		EntityManager em = EFM.createEntityManager();
		EntityTransaction et = null;
		Query query = em.createNativeQuery("DELETE FROM Teachers WHERE Teacher_ID = " + teacherID);
		
		try {
			
			et = em.getTransaction();
			et.begin();
			query.executeUpdate(); 
			et.commit();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public static void reAssignStudents(int oldID, int teacherID, int newTeacherID) {
		EntityManager em = EFM.createEntityManager();
		EntityTransaction et = null;
		Student stud = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			
			stud = getStudentByID(teacherID, oldID);
			stud.setTeacherID(newTeacherID);
			
			em.persist(stud);
			et.commit();
		}catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			
			ex.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public static void changeStudentsID(int oldID, int newID, int teacherID) {
		EntityManager em = EFM.createEntityManager();
		EntityTransaction et = null;
		Student stud = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			//cust = em.find(Customer.class, id);
			stud = getStudentByID(teacherID, oldID);
			//cust.setfName(fname);
			stud.setID(newID);
			
			em.persist(stud);
			et.commit();
		}catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			
			ex.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	
	
}