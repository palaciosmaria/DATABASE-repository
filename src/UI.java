import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import DB.SQLManager;
import transplantation.pojo.Doctor;

public class UI {
	
	private static SQLManager manager;
	
	
	
	public static void main(String args[]) throws IOException, SQLException {
		
		manager=new SQLManager();
		
		manager.connect();
		manager.createTables();

	
//Menu		

	

	int option=0;		
	


		
	do {	
	System.out.println("------MENU------");

	System.out.println("1. Insert the information");
	System.out.println("2. Delete");
	System.out.println("3. Search");
	
	try {
	System.out.println("Insert the option: ");
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String stringoption=reader.readLine();
	option=Integer.parseInt(stringoption);
	}catch(Exception e) {
		e.printStackTrace();
		
	}
	}while(option>1 || option <3);	
	
	switch(option) {
	case 1:
		
		System.out.println("Introduce the doctor's info:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("ID: ");
		String idstring = reader.readLine();
		int id= Integer.parseInt(idstring);
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print("Speciality: ");
		String speciality = reader.readLine();
		Doctor d= new Doctor (id,name, speciality);
		manager.insertDoc(d);
		
		
		
	case 2:
		
		
		
		//tst
	
	
	
	
	
	
	
	
		
		
		
		
	case 3:
		try{
			
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Choose a doctor, type its ID: ");
		List<Doctor> list1= manager.getAllDoctors();
		System.out.println(list1);
		id = Integer.parseInt(reader.readLine());
		
		//call a method in manager that returns a doctor by id
		//print the chosen doctor
		
		System.out.print("Type the new speciality of the doctor: ");
		String newSpeciality = reader.readLine();
		//change the speciality of the chosen doctor
		//calls the updateDoc method
		System.out.println("Update finished.");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	
	}
		
		
		
		
	
		
		
	}
	
	
	public static void menuInsertDoctor() throws IOException {
		
		System.out.println("Introduce the doctor's info:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("ID: ");
		String idstring = reader.readLine();
		int id= Integer.parseInt(idstring);
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print("Speciality: ");
		String speciality = reader.readLine();
		Doctor d= new Doctor (id,name, speciality);
		manager.insertDoc(d);
		
	}
	
	
	
	
	
}
