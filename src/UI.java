import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import DB.JDBCManager;
import DB.JPAManager;
import DB.ManagerInterface;
import DB.XMLManager;

import transplantation.pojo.Doctor;
import transplantation.pojo.Donor;
import transplantation.pojo.Donor_List;
import transplantation.pojo.Hospital;
import transplantation.pojo.Organ;
import transplantation.pojo.Request;

public class UI {
	
	
	
	private static JDBCManager manager;
	private static JPAManager jpamanager;
	private static XMLManager xmlmanager;
	

	public static void main(String args[]) throws IOException, SQLException {

		manager=new JDBCManager();
		manager.connect();
		jpamanager= new JPAManager();
		jpamanager.connect();
		xmlmanager=new XMLManager();
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
			System.out.println("4. Delete a hospital"); 
			System.out.println("5. Search a hospital");
			System.out.println("6. XML: Marshall");
			System.out.println("7. XML: Unmarshall");
			System.out.println("8. DTD checker");
			System.out.println("9. HTML ");
			System.out.println("10. Back to principal menu");
			
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
				deleteHospital();
				break;
			case 5:
				searchHospitalByLoc();
				break;
			case 6:
				marshallMenu();
				break;
			case 7:
				unmarshallMenu();
				break;
			case 8:	
				DTDchecker();
				break;
				
			case 9:	
				xmlmanager.simpleTransform("./xmls/External-Donor.xml", "./xmls/donor.xslt", "./xmls/External-Donor.html");
				break;
			case 10:
				exit=true;
				break;

			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);	
	}
	
	public static void DTDchecker(){
		
	        File xmlFile = new File("./xmls/External-Donor.xml"); 
	        try {
	        	// Create a DocumentBuilderFactory
	            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
	            // Set it up so it validates XML documents
	            dBF.setValidating(true);
	            // Create a DocumentBuilder and an ErrorHandler (to check validity)
	            DocumentBuilder builder = dBF.newDocumentBuilder();
	            DB.CustomErrorHandler customErrorHandler = new DB.CustomErrorHandler();
	            builder.setErrorHandler(customErrorHandler);
	            // Parse the XML file and print out the result
	            Document doc = builder.parse(xmlFile);
	            if (customErrorHandler.isValid()) {
	                System.out.println(xmlFile + " was valid!");
	            }
	        } catch (ParserConfigurationException ex) {
	            System.out.println(xmlFile + " error while parsing!");
	        } catch (SAXException ex) {
	            System.out.println(xmlFile + " was not well-formed!");
	        } catch (IOException ex) {
	            System.out.println(xmlFile + " was not accesible!");
	        }
		
	}
	public static void marshallMenu() {
		try {
		System.out.println("Type how do you want to name the XML document (including .xml");
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			
		String name = reader.readLine();
		System.out.println("Marshalling...");
		List<Donor> list=jpamanager.getAllDonors();
		Donor_List donors= new Donor_List(list);
		xmlmanager.Marshall(donors, name);
		
		}catch(IOException ex) {
			System.out.println("ERROR");
			ex.printStackTrace();
		}catch(JAXBException ex){
		System.out.println("ERROR");
		ex.printStackTrace();
	}
	}
	
	public static void unmarshallMenu() {
		try {
			System.out.println("Type the XML file you want to unmarshall");
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			String name = reader.readLine();
			Donor_List donors=xmlmanager.Unmarshall(name);
			
			
			
			for (Donor d: donors.getListDonor()) {
				jpamanager.insertDonor(d);
			}
			
		}catch(IOException ex) {
			System.out.println("ERROR");
			ex.printStackTrace();
		}catch(JAXBException ex){
		System.out.println("ERROR");
		ex.printStackTrace();
		}catch(RollbackException e){
		System.out.println("\n\nERROR. DONOR ALREADY EXIST IN THE DATABASE.\n\n");
		     }
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
			System.out.println("2. Search an organ");
			System.out.println("3. Show all organs"); 
			System.out.println("4. Delete an organ");
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
				insertOrganMenu();				
				break;
			case 2:
				searchOrganByType();
				break;
			case 3:
				showAllOrgans();
				break;
			case 4:
				deleteOrganMenu();
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
	
	
	public static void donorInfoMenu() {
		int option=0;
		boolean exit=false;
		do {
			System.out.println( "\n-----DONOR INFO MENU------ " );
			System.out.println("Operations: ");
			System.out.println("1. Insert a donor");
			System.out.println("2. Update a donor");
			System.out.println("3. Search a donor by name");
			System.out.println("4. Show all donors"); 
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
				showAllDonors();
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
			System.out.println("5. Delete a patient");
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
				deleteRequestMenu();
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
	
	
	
	public static void doctorInfoMenu() throws NumberFormatException{
		
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
			System.out.println("6. Add hospital to doctor");
			System.out.println("7. Fire a doctor");
			System.out.println("8. Back to principal menu");
			
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
				addHospitalDoctor();
				break;
			case 7:
				deleteHospitalDoctor();
				break;
			case 8:
				exit=true;
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}
		}while(exit==false);
		
	}
	public static void deleteHospitalDoctor() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose the hospital where the doctor works:");
			List<Hospital> list = manager.getAllHospitals();
			System.out.println(list);
			System.out.println("Write the id:");
			int idhospital = Integer.parseInt(reader.readLine());
			System.out.println("Choose the doctor:");
			List<Doctor> list1 = manager.getAllDoctors();
			System.out.println(list1);
			System.out.println("Write the id:");
			int doctorid = Integer.parseInt(reader.readLine());
			Doctor d = manager.searchDoctorById(doctorid);
			Hospital h = manager.searchHospitalById(idhospital);
			manager.deleteRelationship(h,d);
			
			}catch(SQLException ex) {
				System.out.println("Error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void addHospitalDoctor() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose the hospital where the doctor works:");
			List<Hospital> list = manager.getAllHospitals();
			System.out.println(list);
			System.out.println("Write the id:");
			int idhospital = Integer.parseInt(reader.readLine());
			System.out.println("Choose the doctor:");
			List<Doctor> list1 = manager.getAllDoctors();
			System.out.println(list1);
			System.out.println("Write the id:");
			int doctorid = Integer.parseInt(reader.readLine());
			Doctor d = manager.searchDoctorById(doctorid);
			Hospital h = manager.searchHospitalById(idhospital);
			manager.insertRelationship(h,d);
			
			}catch(SQLException ex) {
				System.out.println("Error");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
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
			jpamanager.showAllDonors();
			System.out.println("Introduce the donor's id:");
			String stringdonorid = reader.readLine();
			Integer donorId= Integer.parseInt(stringdonorid);
			Donor d =  jpamanager.readDonorbyId(donorId);
			Integer doctorId;
			Doctor doc=null;
			Integer requestId;
			Request r=null;
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
				doc= manager.searchDoctorById(doctorId);
				
				
				break;
			}else{
				System.out.println("Caracter introduced is not valid.");
			}}while(true);
		
			
			Organ or= new Organ(typeOforgan,lifeSpan,d,doc);
			jpamanager.insertOrgan(or);
			System.out.println("Organ inserted correctly");
			
			System.out.println("\n Matches of requests:");
			System.out.println(manager.checkOrgan(or));
			List <Request> list= new ArrayList<Request>();
					list = manager.checkOrgan(or);
			
			if(list.size()!=0) {
			System.out.println("Choose id of the request you want: ");
			int id_request= Integer.parseInt(reader.readLine());
			Request rq = jpamanager.readRequestById(id_request);
			rq.setOrgan(or);
			System.out.println("The matching was completed successfully");
			System.out.println(rq);}
			else {
				System.out.println("There aren´t requests for this organ. Thankfully, none needs an organ :)");
			}
			
			
			
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
			Date date = null;
			do {
				try {
				
				System.out.print("Date of Birth(dd-MM-yyyy): ");
				String stringdob = reader.readLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");			
				LocalDate ldate = LocalDate.parse(stringdob, formatter);
			    date = Date.valueOf(ldate);
				
				}catch(DateTimeParseException ex) {
					System.out.println("Not a valid date");
					date = null;
				}
				}while(date==null);
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
		}}
	
		public static void searchOrganByType() {
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Write the organ's type: ");
				String type = reader.readLine();
				System.out.println("Matching organs:");
				List<Organ> or = jpamanager.readOrganbyType(type);
				for (Organ organ : or) {
					System.out.println(organ);
				}
				
			}catch(IOException e){
				e.printStackTrace();
			}
	}
	public static void showAllDonors() {
		try {
			System.out.println("List of all the Donors");
			jpamanager.showAllDonors();
			}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void showAllOrgans() {
		try {
			System.out.println("List of all the Organs");
			jpamanager.showAllOrgans();
			}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void updateDonorMenu() {
	try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		jpamanager.showAllDonors();
		System.out.println("Choose a Donor to change itÂ´s location. Write the id: ");
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
	public static void deleteOrganMenu() {
		try{
			System.out.println("Organs:");
			jpamanager.showAllOrgans();
			System.out.print("Choose an organ to delete. Type it's ID:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int or_id = Integer.parseInt(reader.readLine());
			jpamanager.deleteOrgan(or_id);
			System.out.print("Organ deleted correctly.");
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
				System.out.print("");
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
				System.out.print("");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void deleteHospital() {
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
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static void searchHospitalByLoc() {
		try{
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name of the location of the hospital to be shown: ");
			String hloc = r.readLine();
			List<Hospital> list= manager.searchHospitalByLoc (hloc);
			for (Hospital hospital : list) {
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
			Date date = null;
			do {
			try {
			
			System.out.print("Date of Birth(dd-MM-yyyy): ");
			String stringdob = reader.readLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");			
			LocalDate ldate = LocalDate.parse(stringdob, formatter);
		    date = Date.valueOf(ldate);
			
			}catch(DateTimeParseException ex) {
				System.out.println("Not a valid date");
				date = null;
			}
			}while(date==null);
			System.out.print("Blood Type: ");
			String bt = reader.readLine();
			System.out.print("Organ needed: ");	
			String organneeded = reader.readLine();
			System.out.print("Priority: ");	
			int priority=Integer.parseInt(reader.readLine());
			
			Request rq=new Request(name, date, bt, organneeded,priority);
		
			jpamanager.insertRequest(rq);
			
			System.out.println("Patient inserted correctly");
			System.out.println("\n Matches:");
			System.out.println(manager.checkRequest(rq));
			List<Organ> list = new ArrayList<Organ>();
			list = manager.checkRequest(rq);
			if(list.size()!=0) {
			System.out.println("Choose id of the organ you want: ");
			int id_organ= Integer.parseInt(reader.readLine());
			Organ or = jpamanager.readOrganById(id_organ);
			or.setRequest(rq);
			System.out.println("The matching was completed successfully");
			System.out.println(or.toStringComplete());
			}
			else {
				System.out.println("There aren´t matching organs, sorry");
			}
			
			}catch(IOException e){
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public static void showAllRequestsMenu() {
		try {
			System.out.println("List of all the requests");
			List<Request> list1= manager.getAllRequests();
			for (Request request : list1) {
				System.out.println(request);
				System.out.print("");
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
			System.out.println("Choose a request to change itÂ´s priority. Write the id: ");
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
	
	
	public static void deleteRequestMenu(){
		try{
			showAllRequestsMenu();
			System.out.print("Choose a request to delete. Type it's ID:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int rq_id = Integer.parseInt(reader.readLine());
			jpamanager.deleteRequest(rq_id);
			System.out.print("Request deleted correctly.");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	
}