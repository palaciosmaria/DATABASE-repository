import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
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
		//principalMenu();

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
	System.out.println("12. Update donor(loc)");
	System.out.println("13. Delete donor");
	System.out.println("14. Delete hospital");
	System.out.println("15. Search hospital by location");
	System.out.println("16. Show all Donors");
	System.out.println("17. Update organ");
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
			jpamanager.showAllDonors();
			System.out.println("Introduce the donor's id:");
			String stringdonorid = reader.readLine();
			int donorId= Integer.parseInt(stringdonorid);
			Donor d = jpamanager.readDonorbyId(donorId);
			/*try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				jpamanager.showAllDonors();
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
			}*/
			
			
			//Integer doctorId;
			/*do{
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
				break;
			}else{
				System.out.println("Caracter introduced is not valid.");
			}}while(true);
			
			Integer requestId;
			do{
				System.out.println("Do you want to insert a request?");
				String yesno = reader.readLine();
				if(yesno.equals("no")|| yesno.equals("NO")){
					requestId= null;
					break;}
				if(yesno.equals("yes")|| yesno.equals("YES")){
					System.out.println(manager.getAllRequests());
					System.out.println("Introduce the request's id:");
					String stringrequestid = reader.readLine();
					requestId= Integer.parseInt(stringrequestid);
					break;
				}else{
					System.out.println("Caracter introduced is not valid.");
				}}while(true);
			*/
			
			Organ o= new Organ(typeOforgan,lifeSpan,d);
			d.addOrgan(o);
			System.out.println("Organ inserted correctly");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		break;
	case 10:
		try {
		System.out.println("Introduce the donor's info:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name: ");
		String name = reader.readLine();
		Date date=null;
		do{
			try{
		System.out.print("Date of Birth (dd-MM-yyyy): ");
		String stringdob = reader.readLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDate ldate = LocalDate.parse(stringdob, formatter);
		date = Date.valueOf(ldate);
		break;
			}catch(DateTimeException ex){
				System.out.print("Date of Birth not valid.  ");
				
			}
		}while(true);
		
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
		break;
	case 12:
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			jpamanager.showAllDonors();
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
try{
		System.out.println("Hospital's donors:");
		jpamanager.showAllDonors();
		System.out.print("Choose a donor to delete. Type it's ID:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int dn_id = Integer.parseInt(reader.readLine());
		jpamanager.deleteDonor(dn_id);
		System.out.print("Donor deleted correctly.");
		}catch(IOException e){
			e.printStackTrace();
		}
		break;
	case 14:
		try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Choose a hospital to delete, type its ID: ");
		List<Hospital> list= manager.getAllHospitals();
		for (Hospital hospital : list) {
			System.out.println(hospital);
		}
		int id = Integer.parseInt(reader.readLine());
		//call delete method
		Hospital h= new Hospital(id);
		manager.deleteHospital(h);
		System.out.println("Deletion finished.");
		
	}catch(IOException e){
		e.printStackTrace();
		
	}
		break;
	case 15:
		try{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name of the location of the hospital to be shown: ");
		String hloc = r.readLine();
		List<Hospital> list= manager.searchHospitalByLoc (hloc);
		for (Hospital hospital : list) {
			System.out.println(hospital);
			
		}
		break;
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	case 16: 
		try {
		System.out.println("List of all the Donors");
		jpamanager.showAllDonors();
		
		break;
	}catch(Exception e){
		e.printStackTrace();
	}
	case 17:
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Aqui");
			jpamanager.showAllOrgans();
			System.out.println("Choose a donor:");
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
		
	
	}
	}
	
}
	
	/*
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
			System.out.println("2. Update a patient-ABSENT");
			System.out.println("3. Search a patient-ABSENT");
			System.out.println("4. Show all patient-ABSENT"); 
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
		
	}*/
	/*
	public static void insertOrganMenu () {
		try{
			System.out.println("Introduce the organs's info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Type of organ: ");
			String typeOforgan = reader.readLine();
			System.out.print("Life span (in minutes): ");
			String stringlifeSpan = reader.readLine();
			Integer lifeSpan= Integer.parseInt(stringlifeSpan);
			jpamanager.showAllDonors();
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
		jpamanager.showAllDonors();
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
	}
	}
	
	public static void deleteDonorMenu() {
	try{
		System.out.println("Hospital's donors:");
		jpamanager.showAllDonors();
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
}*/