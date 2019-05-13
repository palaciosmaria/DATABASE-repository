package DB;

import java.util.List;

import transplantation.pojo.*;


public interface ManagerInterface {
	
	//JDBC methods
	public void connect();
	public void createTables();
	public void deleteDoc(Doctor d);
	public List<Doctor> getAllDoctors();
	public List<Donor> getAllDonors();
	public List<Hospital> getAllHospitals();
	public List<Request> getAllRequests();
	public void insertDoc(Doctor d);
	public void insertHosp(Hospital h);
	public void insertOrgan(Organ o);
	public List<Doctor> searchDoctorByName(String s);
	public void updateDoc(Doctor d);
	public void updateHosp (Hospital h);
	
	
	//JPA methods
	public void insertRequest();
}
