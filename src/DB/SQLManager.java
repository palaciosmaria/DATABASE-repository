package DB;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import transplantation.pojo.Doctor;

public class SQLManager {
	Connection c;
	public void connect() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/transplantation.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		public void createTables(){
			
			try {
		
			Statement donor = c.createStatement();	
			String sqldonor = "CREATE TABLE donor "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " date of birth  DATE	 NOT NULL,"
					   + " blood type  TEXT NOT NULL,"
					   + " location TEXT NOT NULL)";
				donor.executeUpdate(sqldonor);
				donor.close();
				
				Statement doctor = c.createStatement();	
				String sqldoctor = "CREATE TABLE doctor "
						   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
						   + " name     TEXT     NOT NULL, "
						   + " speciality TEXT NOT NULL)";
					doctor.executeUpdate(sqldoctor);
					doctor.close();
					
				Statement hospital = c.createStatement();	
				String sqlhospital = "CREATE TABLE hospital"
							
							   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
							   + " name     TEXT     NOT NULL, "
							   + " location TEXT NOT NULL)";
						hospital.executeUpdate(sqlhospital);
						hospital.close();
						
				Statement receiver = c.createStatement();	
				String sqlreceiver = "CREATE TABLE receiver "
								   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
								   + " name     TEXT     NOT NULL, "
								   + " date of birth  DATE	 NOT NULL,"
								   + " blood type  TEXT NOT NULL,"
								   + " organ needed TEXT NOT NULL,"
								   + " priority INTEGER NOT NULL"
								   + " id_hospital INTEGER NOT NULL,"
								   + " FOREIGN KEY (id_hospital) REFERENCES hospital (id)"
								   + " ON UPDATE RESTRICT ON DELETE CASCADE)";				
						receiver.executeUpdate(sqlreceiver);
						receiver.close();
				
				Statement organ = c.createStatement();
				String sqlorgan = "CREATE TABLE organ"
								+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
								+ " type of organ TEXT NOT NULL,"
								+ " life span(minutes) INTEGER NOT NULL,"
								+ " id_donor INTEGER NOT NULL,"
								+ " id_doctor INTEGER NOT NULL, "
								+ " id_receiver INTEGER NOT NULL,"
								+ " FOREIGN KEY (id_donor) REFERENCES donor (id)"
								+ " ON UPDATE RESTRICT ON DELETE CASCADE,"
								+ " FOREIGN KEY (id_doctor) REFERENCES doctor (id) "
								+ " ON UPDATE RESTRICT ON DELETE CASCADE,"
								+ " FOREIGN KEY (id_receiver) REFERENCES receiver (id) "
								+  " ON UPDATE RESTRICT ON DELETE CASCADE)";
				organ.executeUpdate(sqlorgan);
				organ.close();
				
				Statement relationship = c.createStatement();
				String sqlrelationship = "CREATE TABLE relationship"
										+ "(id_doctor INTEGER,"
										+ " id_hospital INTEGER, "
										+ " FOREIGN KEY (id_doctor) REFERENCES doctor (id) "
										+ " ON UPDATE RESTRICT ON DELETE CASCADE,"
										+ " FOREIGN KEY (id_hospital) REFERENCES hospital (id) "
										+ " ON UPDATE RESTRICT ON DELETE CASCADE,"
										+ "PRIMARY KEY (id_doctor, id_hospital)";
				relationship.executeUpdate(sqlrelationship);
				relationship.close();
				
				
				System.out.println("Database connection closed.");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
	
	
			}
		
		public void insertDoc( Doctor d){
			try{
				
				String sql = "INSERT INTO doctor (name, speciality) "
						+ "VALUES (?,?);";//each question mark will be replaced with a string
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setString(1, d.getName());
				prep.setString(2, d.getSpeciality());
				prep.executeUpdate();//without parameters because you already pass the parameters before
				prep.close();
				System.out.println("Doctor info processed");
				System.out.println("Record inserted.");
			
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public void selectDoc( Doctor d){
			try {
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM doctor";
			ResultSet rs = stmt.executeQuery(sql);//we only ask question to the database
			while (rs.next()) {//this methods returns true if there are any switchs
				int id = rs.getInt("id");//here we can put 1 because id is the 1 column
				String name = rs.getString("name");
				String speciality = rs.getString("speciality");
				Doctor newd= new Doctor(id, name, speciality);
				System.out.println(newd);
			}
			rs.close();
			stmt.close();//after using a statement we close it
			System.out.println("Search finished.");
			}
			catch(Exception e)
			{e.printStackTrace();}			}
		
		

}
			
			

		 

