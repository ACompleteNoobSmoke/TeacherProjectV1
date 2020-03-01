import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
 
	public static boolean getTeacher(int id) {
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
}