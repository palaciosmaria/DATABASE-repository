import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.Query;

import DB.SQLManager;
import transplantation.pojo.Doctor;
import transplantation.pojo.Hospital;

public class UI {
	
	private static SQLManager manager;
	
	
	
	public static void main(String args[]) throws IOException, SQLException {
		
		manager=new SQLManager();
		manager.connect();
		

//Menu		

	int option = 0;		
	
	do {	
	System.out.println("------MENU------");
	System.out.println("0. Create the tables");
	System.out.println("1. Insert doc");
	System.out.println("2. Insert hospital");
	System.out.println("3. Update doctor");
	System.out.println("4. Delete");
	System.out.println("5. Update hospital");
	System.out.println("6. Show all Doctors");
	System.out.println("7. Show all Hospitals");
	
	try {
	System.out.println("Insert the option: ");
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String stringoption=reader.readLine();
	option=Integer.parseInt(stringoption);
	}catch(Exception e) {
		e.printStackTrace();
		
	}
	}while(option<0 || option>7);	
	
	switch(option) {
	
	case 0: 
		manager.createTables();
	case 1:
		try{
			
		manager.getAllDoctors();
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
	
	}
	}
}

