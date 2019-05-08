package DB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
