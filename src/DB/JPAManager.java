package DB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import transplantation.pojo.*;

public class JPAManager {
	
	private static final String PERSISTENCE_PROVIDER = "hospital-provider";
	private static EntityManagerFactory factory;
	private EntityManager em;
	
	public void connect() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}
	//insert es lo mismo que create
	public void insertDonor(Donor dn) {
		em.getTransaction().begin();
		em.persist(dn);
		em.getTransaction().commit();
	}
		public void insertRequest(Request rq) {
			em.getTransaction().begin();
			em.persist(rq);
			em.getTransaction().commit();
		}

	
	public void insertOrgan(Organ o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
		

	}
	
	public void updateDonor(Donor dn, String loc) {
	em.getTransaction().begin();
	dn.setLocation(loc);
	em.getTransaction().commit();
	}
	
	public void updateRequest(Request r, Integer p) {
		em.getTransaction().begin();
		r.setPriority(p);
		em.getTransaction().commit();
		}
	
	
		//read es como si fuese search
	public List<Donor> readDonorbyName(String name){
		
		Query q1 = em.createNativeQuery("SELECT * FROM donor WHERE name LIKE ?", Donor.class);
		q1.setParameter(1, "%" + name + "%");
		List<Donor> dns = (List<Donor>) q1.getResultList();
		// Print the departments
		
		return dns;
		
	}
	
public List<Request> readRequestByName(String name){
		
		Query q1 = em.createNativeQuery("SELECT * FROM request WHERE name LIKE ?", Request.class);
		q1.setParameter(1, "%" + name + "%");
		List<Request> r = (List<Request>) q1.getResultList();
		return r;
		
	}
	
	public Donor readDonorbyId(int id){
		
		Query q1 = em.createNativeQuery("SELECT * FROM donor WHERE id LIKE ?", Donor.class);
		q1.setParameter(1, id);
		Donor dn=(Donor) q1.getSingleResult();
		return dn;
	}
	
	public Request readRequestById(int id){
		
		Query q1 = em.createNativeQuery("SELECT * FROM request WHERE id LIKE ?", Request.class);
		q1.setParameter(1, id);
		Request r=(Request) q1.getSingleResult();
		return r;
	}
	
	public void showAllDonors() {
		Query q1 = em.createNativeQuery("SELECT * FROM donor", Donor.class);
		List<Donor> list = (List<Donor>) q1.getResultList();
		
		for (Donor donor : list) {
			System.out.println(donor);
		}
	}
	
	public List<Donor> getAllDonors() {
		Query q1 = em.createNativeQuery("SELECT * FROM donor", Donor.class);
		List<Donor> list = (List<Donor>) q1.getResultList();
		return list;
	}
	
	
	public void showAllOrgans() {
		Query q1 = em.createNativeQuery("SELECT * FROM organ", Organ.class);
		List<Organ> list = (List<Organ>) q1.getResultList();
		
		for (Organ organ : list) {
			System.out.println(organ);
		}
	}
	public void deleteDonor(int id){
		Query q2 = em.createNativeQuery("SELECT * FROM donor WHERE id = ?", Donor.class);
		q2.setParameter(1, id);
		Donor donor = (Donor) q2.getSingleResult();
		em.getTransaction().begin();
		em.remove(donor);
		em.getTransaction().commit();

	}
	public void deleteRequest (int id){
		Query q2 = em.createNativeQuery("SELECT * FROM request WHERE id = ?", Request.class);
		q2.setParameter(1, id);
		Request request = (Request) q2.getSingleResult();
		em.getTransaction().begin();
		em.remove(request);
		em.getTransaction().commit();

	}
	
	
}
