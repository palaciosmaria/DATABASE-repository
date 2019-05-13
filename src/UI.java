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
import transplantation.pojo.Request;

public class UI {
	
	
	
	private static JDBCManager manager;
	private static JPAManager jpamanager;
	
	

	public static void main(String args[]) throws IOException, SQLException {

		manager=new JDBCManager();
		manager.connect();
		jpamanager= new JPAManager();
		jpamanager.connect();
		principalMenu();
	}
	
	public static void principalMenu() {
		
	int option=0;
	boolean exit=false;
	
	do {
		System.out.println("\n-----DATA BASE------ ");
		System.out.println("0. Create tables");
		System.out.println("1. DataBase Administrator");
		System.out.println("2. Hospital Manager");
		System.out.println("3. Exit");
		try {
			System.out.println("Select identity: ");
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			option=Integer.parseInt(reader.readLine());	
		}catch(IOException ex) {
			System.out.println("ERROR");
		}
		
		switch(option) {
		case 0: 
			manager.createTables();
			break;

		case 1:
			administratorMenu();
			break;
		case 2:
			hospitalManagerMenu();
			break;
		
		case 3:
			exit=true;
			break;
		default:
			System.out.println("Not a valid option");
			break;
		}
	}while(exit==false);	
		
	}
	
	public static void administratorMenu() {
		int option=0;
		boolean exit=false;

		
		do {
			System.out.println( "\n-----ADMINISTRATOR MENU------ " );
			System.out.println("Operations: ");
			System.out.println("1. Insert a hospital");
			System.out.println("2. Update the name of a hospital");
			System.out.println("3. Show all hospitals");
			System.out.println("4. Delete a hospital-ABSENT"); //NO LO HEMOS HECHO
			System.out.println("5. Search a hospital-ABSENT"); //NO LO HEMOS HECHO
			System.out.println("6. Back to principal menu");
			try {
				System.out.println("Select option: ");
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(reader.readLine());	
			}catch(IOException ex) {
				System.out.println("ERROR");
			}
			
			switch(option) {
			case 1:
				insertHospitalMenu();
				break;
			case 2:
				updateHospitalMenu();
				break;
			case 3:
				showAllHospitals();
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);	
		
	}
	
	public static void hospitalManagerMenu() {
		int option=0;
		boolean exit=false;
		
		do {
			System.out.println("\n-----HOSPITAL MANAGER MENU------ ");
			System.out.println("1. Boss");
			System.out.println("2. Doctor");
			System.out.println("3. Back to principal menu");
			try {
				System.out.println("Select identity: ");
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(reader.readLine());	
			}catch(IOException ex) {
				System.out.println("ERROR");
			}
			
			switch(option) {
			case 1:
				bossMenu();
				break;
			case 2:
				doctorMenu();
				break;
			case 3:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);	
	}
	
	public static void doctorMenu() {
		int option=0;
		boolean exit=false;
		do {
			System.out.println( "\n-----DOCTOR MENU------ " );
			System.out.println("Operations: ");
			System.out.println("1. Manage patients information");
			System.out.println("2. Manage donors information");
			System.out.println("3. Manage organs information"); 
			System.out.println("4. Back to principal menu");
			
			try {
				System.out.println("Select option: ");
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(reader.readLine());	
			}catch(IOException ex) {
				System.out.println("ERROR");
			}
			
			switch(option) {
			case 1:
				patientInfoMenu();
				break;
			case 2:
				donorInfoMenu();
				break;
			case 3:
				organInfoMenu();
				break;
			case 4:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);
	}
	
	
	public static void bossMenu () {
		int option=0;
		boolean exit=false;

		
		do {
			System.out.println( "\n-----BOSS MENU------ " );
			System.out.println("Operations: ");
			System.out.println("1. Manage doctors information");
			System.out.println("2. Manage patients information");
			System.out.println("3. Manage donors information");
			System.out.println("4. Manage organs information"); 
			System.out.println("5. Back to principal menu");
			
			try {
				System.out.println("Select option: ");
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(reader.readLine());	
			}catch(IOException ex) {
				System.out.println("ERROR");
			}
			
			switch(option) {
			case 1:
				doctorInfoMenu();
				break;
			case 2:
				patientInfoMenu();
				break;
			case 3:
				donorInfoMenu();
				break;
			case 4:
				organInfoMenu();
				break;
			case 5:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);
	}
	
	public static void organInfoMenu() {
		int option=0;
		boolean exit=false;
		do {
			System.out.println( "\n-----ORGAN INFO MENU------ " );
			System.out.println("Operations: ");
			System.out.println("1. Insert an organ");
			System.out.println("2. Update an organ-ABSENT");
			System.out.println("3. Search an organ-ABSENT");
			System.out.println("4. Show all organs-ABSENT"); 
			System.out.println("5. Delete an organ-ABSENT");
			System.out.println("6. Back to principal menu");
			
			try {
				System.out.println("Select option: ");
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(reader.readLine());	
			}catch(IOException ex) {
				System.out.println("ERROR");
			}
			
			switch(option) {
			case 1:
				insertOrganMenu();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);
	}
	
	
	public static void donorInfoMenu() {
		int option=0;
		boolean exit=false;
		do {
			System.out.println( "\n-----DONOR INFO MENU------ " );
			System.out.println("Operations: ");
			System.out.println("1. Insert a donor");
			System.out.println("2. Update a donor");
			System.out.println("3. Search a donor by name");
			System.out.println("4. Show all donors-ABSENT"); 
			System.out.println("5. Delete a donor");
			System.out.println("6. Back to principal menu");
			
			try {
				System.out.println("Select option: ");
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(reader.readLine());	
			}catch(IOException ex) {
				System.out.println("ERROR");
			}
			
			switch(option) {
			case 1:
				insertDonorMenu();
				break;
			case 2:
				updateDonorMenu();
				break;
			case 3:
				searchDonorsByName();
				break;
			case 4:
				break;
			case 5:
				deleteDonorMenu();
				break;
			case 6:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);
	}
	
	public static void patientInfoMenu() {
		int option=0;
		boolean exit=false;
		do {
			System.out.println( "\n-----PATIENT INFO MENU------ " );
			System.out.println("Operations: ");
			System.out.println("1. Insert a patient");
			System.out.println("2. Update a patient");
			System.out.println("3. Search a patient");
			System.out.println("4. Show all patients"); 
			System.out.println("5. Delete a patient-ABSENT");
			System.out.println("6. Back to principal menu");
			
			try {
				System.out.println("Select option: ");
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(reader.readLine());	
			}catch(IOException ex) {
				System.out.println("ERROR");
			}
			
			switch(option) {
			case 1:
				insertRequestMenu();
				break;
			case 2:
				updateRequestMenu();
				break;
			case 3:
				System.out.println("By id (press 1) o by name(press 2)?");
				try {
					BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
					int o =Integer.parseInt(reader.readLine());
					if (o==1) {
						searchRequestById();
					}else if(o==2) {
						searchRequestsByName();
					}else {
						System.out.println("not a valid option");
					}
				}catch(IOException ex) {
					System.out.println("ERROR");
				}
				break;
			case 4:
				showAllRequestsMenu();
				break;
			case 5:
				break;
			case 6:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);
	}
	
	
	
	public static void doctorInfoMenu() {
		
		int option=0;
		boolean exit=false;

		
		do {
			System.out.println( "\n-----DOCTORS INFO MENU------ " );
			System.out.println("Operations: ");
			System.out.println("1. Insert a doctor");
			System.out.println("2. Update a doctor");
			System.out.println("3. Search a doctor by name");
			System.out.println("4. Show all doctors"); 
			System.out.println("5. Delete a doctor");
			System.out.println("6. Back to principal menu");
			
			try {
				System.out.println("Select option: ");
				BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(reader.readLine());	
			}catch(IOException ex) {
				System.out.println("ERROR");
			}
			
			switch(option) {
			case 1:
				insertDoctorMenu();
				break;
			case 2:
				updateDoctorMenu();
				break;
			case 3:
				searchDoctorByName();
				break;
			case 4:
				showAllDoctorsMenu();
				break;
			case 5:
				deleteDoctorMenu();
				break;
			case 6:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);
		
	}
	
	public static void insertOrganMenu () {
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
			Integer doctorId;
			do{
			System.out.println("Do you want to insert a doctor?");
			String yesno = reader.readLine();
			if(yesno.equals("no")|| yesno.equals("NO")){
				doctorId= null;
				break;}
			if(yesno.equals("yes")|| yesno.equals("YES")){
				System.out.println(manager.getAllDoctors());
				System.out.println("Introduce the doctor's id:");
				String stringdoctorid = reader.readLine();
				doctorId= Integer.parseInt(stringdoctorid);
			}else{
				System.out.println("Caracter introduced is not valid.");
			}}while(true);
			//do the same with request!!!!!!!!!
			System.out.println(manager.getAllRequests());
			System.out.println("Introduce the request's id:");
			String stringrequestid = reader.readLine();
			Integer requestId= Integer.parseInt(stringrequestid);
			Organ o= new Organ(typeOforgan,lifeSpan,donorId,doctorId,requestId);
			System.out.println("Organ inserted correctly");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void insertDonorMenu() {
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
			}catch(IOException e){
				e.printStackTrace();
			}
	}
	
	public static void searchDonorsByName() {
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
	}
	public static void updateDonorMenu() {
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
	}catch(IOException e){
		e.printStackTrace();
	}catch(SQLException e){
		e.printStackTrace();
	}
	}
	
	public static void deleteDonorMenu() {
	try{
		System.out.println("Hospital's donors:");
		jpamanager.getAllDonors();
		System.out.print("Choose a donor to delete. Type it's ID:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int dn_id = Integer.parseInt(reader.readLine());
		jpamanager.deleteDonor(dn_id);
		System.out.print("Donor deleted correctly.");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void insertDoctorMenu() {
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
		}catch(IOException e){
				e.printStackTrace();
			}
	}
	public static void updateDoctorMenu() {
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
			}catch(IOException e){
				e.printStackTrace();
			}catch(SQLException sql) {
				System.out.println("Error con el manager");
			}
	}
	
	public static void deleteDoctorMenu() {
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
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static void showAllDoctorsMenu() {
		try {
			System.out.println("List of all the Doctors");
			List<Doctor> list1= manager.getAllDoctors();
			for (Doctor doctor : list1) {
				System.out.println(doctor);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void insertHospitalMenu () {
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
			}catch(IOException e){
				e.printStackTrace();
			}
	}
	
	public static void searchDoctorByName(){
		try{
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name of the doctor to be shown: ");
			String dname = r.readLine();
			List<Doctor> list1= manager.searchDoctorByName (dname);
			for (Doctor doctor : list1) {
				System.out.println(doctor);
				
			}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	public static void updateHospitalMenu() {
			try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			List<Hospital> list1= manager.getAllHospitals();
			for (Hospital hospital : list1) {
				System.out.println(hospital);
			}
			//System.out.println(list1);
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
			}catch(IOException e){
				e.printStackTrace();
			}catch(SQLException sql) {
				System.out.println("Error en manager");
			}
	}

	public static void showAllHospitals() {
		try {
			System.out.println("List of all the Hospitals");
			List<Hospital> list1= manager.getAllHospitals();
			for (Hospital hospital : list1) {
				System.out.println(hospital);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void insertRequestMenu() {
		try {
			System.out.println("Introduce the patient's info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("Date of Birth: ");
			String stringdob = reader.readLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");			
			LocalDate ldate = LocalDate.parse(stringdob, formatter);
			Date date = Date.valueOf(ldate);
			System.out.print("Blood Type: ");
			String bt = reader.readLine();
			System.out.print("Organ needed: ");	
			String organneeded = reader.readLine();
			System.out.print("Priority: ");	
			int priority=Integer.parseInt(reader.readLine());
			
			Request rq=new Request(name, date, bt, organneeded,priority);
		
			jpamanager.insertRequest(rq);
			
			System.out.println("Patient inserted correctly");
			}catch(IOException e){
				e.printStackTrace();
			}
		
	}
	
	public static void showAllRequestsMenu() {
		try {
			System.out.println("List of all the requests");
			List<Request> list1= manager.getAllRequests();
			for (Request request : list1) {
				System.out.println(request);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void updateRequestMenu() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			List<Request> list1= manager.getAllRequests();
			for (Request request : list1) {
				System.out.println(request);
			}
			System.out.println("Choose a request to change it´s priority. Write the id: ");
			int request_id=Integer.parseInt(reader.readLine());
			System.out.println("Type the new priority:");
			Integer priority=Integer.parseInt(reader.readLine());
			Request r=jpamanager.readRequestById(request_id);
			jpamanager.updateRequest(r, priority);
			System.out.println(r);
			System.out.println("Priority updated correctly.");
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void searchRequestById() {
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Write the request's id: ");
			Integer id = Integer.parseInt(reader.readLine());
			System.out.println(jpamanager.readRequestById(id));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void searchRequestsByName() {
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Write the request's name: ");
			String name = reader.readLine();
			System.out.println("Matching requests:");
			List<Request> r = jpamanager.readRequestByName(name);
			for (Request request : r) {
				System.out.println(request);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	
}