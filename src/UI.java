import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import DB.JDBCManager;
import DB.JPAManager;
import DB.ManagerInterface;
import transplantation.pojo.Doctor;
import transplantation.pojo.Donor;
import transplantation.pojo.Hospital;
import transplantation.pojo.Organ;

public class UI {
	
	private static JDBCManager manager;
	private static JPAManager jpamanager;
	
	
	public static void main(String args[]) throws IOException, SQLException {
		
		manager=new JDBCManager();
		manager.connect();
		jpamanager= new JPAManager();
		jpamanager.connect();

//Menu		

	int option = 0;		
	
	do {	
	System.out.println("------MENU------");
	System.out.println("0. Create the tables");
	System.out.println("1. Insert doc");
	System.out.println("2. Insert hospital");
	System.out.println("3. Update doctor");
	System.out.println("4. Delete doctor");
	System.out.println("5. Update hospital");
	System.out.println("6. Show all Doctors");
	System.out.println("7. Show all Hospitals");
	System.out.println("8. Search doctor by name");
	System.out.println("9. Insert organ");
	System.out.println("10.Insert Donor");
	//System.out.println("11.Insert request");
	System.out.println("11. Read donor");
	System.out.println("12. Update donor");
	try {
	System.out.println("Insert the option: ");
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String stringoption=reader.readLine();
	option=Integer.parseInt(stringoption);
	}catch(Exception e) {
		e.printStackTrace();
		
	}
	
	}while(option<0 || option>100);	
	
	switch(option) {
	
	case 0: 
		manager.createTables();
		break;
	case 1:
		try{
			
		//manager.getAllDoctors();
		System.out.println("Introduce the doctor's info:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print("Speciality: ");
		String speciality = reader.readLine();
		Doctor d= new Doctor (name, speciality);
		manager.insertDoc(d);
		System.out.println("Doctor inserted correctly");
		break;
		}catch(IOException e){
			e.printStackTrace();
		}
		
	case 2:
		
		try{
		
		System.out.println("Introduce the hospital's info:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print("Location: ");
		String location = reader.readLine();
		Hospital h= new Hospital (name, location);
		manager.insertHosp(h);
		System.out.println("Hospital inserted correctly");
		break;
		
		}catch(IOException e){
			e.printStackTrace();
		}
		
	case 3:
		try{
			
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<Doctor> list1= manager.getAllDoctors();
		System.out.println(list1);
		System.out.println("Choose a doctor, type its ID: ");
		int id = Integer.parseInt(reader.readLine());
		
		//call a method in manager that returns a doctor by id
		//print the chosen doctor
		
		System.out.print("Type the new speciality of the doctor: ");
		String newSpeciality = reader.readLine();
		//change the speciality of the chosen doctor
		//calls the updateDoc method
		Doctor d= new Doctor(id,newSpeciality);
		manager.updateDoc(d);
		
		System.out.println("Update finished.");
		break;
		}catch(IOException e){
			e.printStackTrace();
		}
		
	case 4:
		try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Choose a doctor to delete, type its ID: ");
		List<Doctor> list1= manager.getAllDoctors();
		for (Doctor doctor : list1) {
			System.out.println(doctor);
		}
		int id = Integer.parseInt(reader.readLine());
		//call delete method
		Doctor d= new Doctor(id);
		manager.deleteDoc(d);
		System.out.println("Deletion finished.");
		break;
	}catch(IOException e){
		e.printStackTrace();
	}
	case 5:
		try{
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			List<Hospital> list1= manager.getAllHospitals();
			System.out.println(list1);
			System.out.println("Choose a Hospital, type its ID: ");
			int id = Integer.parseInt(reader.readLine());
			//call a method in manager that returns a hospital by id
			//print the chosen hospital
			System.out.print("Type the new name of the hospital: ");
			String newName = reader.readLine();
			//change the name of the chosen hospital
			//calls the updateHosp method
			Hospital h= new Hospital(id,newName);
			System.out.println(h);
			manager.updateHosp(h);
			System.out.println("Update finished.");
			break;
			}catch(IOException e){
				e.printStackTrace();
			}
		
	case 6: 
		try {
		System.out.println("List of all the Doctors");
		List<Doctor> list1= manager.getAllDoctors();
		for (Doctor doctor : list1) {
			System.out.println(doctor);
		}
		break;
	}catch(Exception e){
		e.printStackTrace();
	}
	
	case 7: 
		try {
		System.out.println("List of all the Hospitals");
		List<Hospital> list1= manager.getAllHospitals();
		for (Hospital hospital : list1) {
			System.out.println(hospital);
		}
		break;
	}catch(Exception e){
		e.printStackTrace();
	}
	case 8:
		try{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name of the doctor to be shown: ");
		String dname = r.readLine();
		List<Doctor> list1= manager.searchDoctorByName (dname);
		for (Doctor doctor : list1) {
			System.out.println(doctor);
			
		}
		break;
		}catch(Exception e){
			e.printStackTrace();
		}
	case 9:
		try{
			
			System.out.println("Introduce the organs's info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Type of organ: ");
			String typeOforgan = reader.readLine();
			System.out.print("Life span (in minutes): ");
			String stringlifeSpan = reader.readLine();
			Integer lifeSpan= Integer.parseInt(stringlifeSpan);
			System.out.println(manager.getAllDonors());
			System.out.println("Introduce the donor's id:");
			String stringdonorid = reader.readLine();
			Integer donorId= Integer.parseInt(stringdonorid);
			System.out.println(manager.getAllDoctors());
			System.out.println("Introduce the doctor's id:");
			String stringdoctorid = reader.readLine();
			Integer doctorId= Integer.parseInt(stringdoctorid);
			System.out.println(manager.getAllRequests());
			System.out.println("Introduce the request's id:");
			String stringrequestid = reader.readLine();
			Integer requestId= Integer.parseInt(stringrequestid);
			Organ o= new Organ(typeOforgan,lifeSpan,donorId,doctorId,requestId);
			
			System.out.println("Organ inserted correctly");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	case 10:
		try {
		System.out.println("Introduce the donor's info:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print("Date of Birth: ");
		String stringdob = reader.readLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		//HAY Q ACABAR LO DE LA DATE ESTÁ EN EL CHEATSHEET
		LocalDate ldate = LocalDate.parse(stringdob, formatter);
		Date date = Date.valueOf(ldate);
		System.out.print("Blood Type: ");
		String bt = reader.readLine();
		System.out.print("Location: ");	
		String location = reader.readLine();
		
		Donor dn = new Donor(name, date , bt, location );
		jpamanager.insertDonor(dn);
		
		System.out.println("Donor inserted correctly");
		break;
		}catch(IOException e){
			e.printStackTrace();
		}
		
	case 11:
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Write the donor's name: ");
			String name = reader.readLine();
			System.out.println("Matching donors:");
			List<Donor> dns = jpamanager.readDonorbyName(name);
			for (Donor donor : dns) {
				System.out.println(donor);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	case 12:
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(manager.getAllDonors());
			System.out.println("Choose a Donor to change it´s location. Write the id: ");
			int donor_id=Integer.parseInt(reader.readLine());
			System.out.println("Type the new location:");
			String newLocation=reader.readLine();
			Donor dn=jpamanager.readDonorbyId(donor_id);
			jpamanager.updateDonor(dn, newLocation);
			System.out.println(dn);
			System.out.println("Location updated correctly.");
			break;
		}catch(IOException e){
			e.printStackTrace();
		}
		
	case 13:
	}
	}
}

