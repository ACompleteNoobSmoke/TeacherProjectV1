package repo;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	
	public static boolean checkStudentID(int id, int teacherID) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.id = :ID AND s.teacherid = :TeacherID";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("ID", id);
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

	public static Student getStudent(int teacherID, int studentID, String studentName) {
		EntityManager em = EFM.createEntityManager();
		String query = "SELECT s FROM Student s WHERE s.id = :ID  OR s.fullname = :StudentName AND s.teacherid = :TeacherID";
		
		TypedQuery<Student> tq = em.createQuery(query, Student.class);
		tq.setParameter("ID", studentID);
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
	
	
	
}