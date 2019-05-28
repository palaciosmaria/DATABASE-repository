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
	public void deleteDonor();
	public void deleteOrgan();
	public void deleteRequest();
	public void insertRequest();
	public void updateDonor(Donor dn, String loc);
	public void updateRequest(Request r, Integer p);
	public List<Donor> readDonorbyName(String name);
	public List<Organ> readOrganbyType(String type);
	public List<Request> readRequestByName(String name);
	public Donor readDonorbyId(int id);
	public Request readRequestById(int id);
	public Organ readOrganById(int id);
	public void showAllDonors();
	public void showAllOrgans();
	public void deleteDonor(int id);
	public void deleteOrgan(int id);
	public void deleteRequest (int id);
	
	//XML methods
	public void Marshall(Donor_List dns, String ruta);
	public Donor_List Unmarshall(String ruta);
	public void simpleTransform(String sourcePath, String xsltPath,String resultDir) ;
	
	
}
