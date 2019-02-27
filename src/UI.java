import java.io.BufferedReader;
import java.io.InputStreamReader;

import DB.SQLManager;
import transplantation.pojo.Doctor;

public class UI {
	
	private static SQLManager manager;
	
	
	
	public static void main(String args[]) {
		
		manager=new SQLManager();
		
		manager.connect();
		manager.createTables();
		
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
