package DB;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	public void updateDonor(Donor dn, String loc) {
	em.getTransaction().begin();
	dn.setLocation(loc);
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
	
	public Donor readDonorbyId(int id){
		
		Query q1 = em.createNativeQuery("SELECT * FROM donor WHERE id LIKE ?", Donor.class);
		q1.setParameter(1, id);
		Donor dn=(Donor) q1.getSingleResult();
		
		
		return dn;
		
	}
}
