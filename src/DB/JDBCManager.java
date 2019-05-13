package DB;



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplantation.pojo.Doctor;
import transplantation.pojo.Donor;
import transplantation.pojo.Hospital;
import transplantation.pojo.Organ;
import transplantation.pojo.Request;

public class JDBCManager {
	static Connection c;
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
					   + " datebirth  DATE	 NOT NULL,"
					   + " bloodtype  TEXT ,"
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
						
				Statement request = c.createStatement();	
				String sqlrequest = "CREATE TABLE request "
								   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
								   + " name     TEXT     NOT NULL, "
								   + " datebirth  DATE	 NOT NULL,"
								   + " bloodtype  TEXT ,"
								   + " organeeded TEXT NOT NULL,"
								   + " priority INTEGER NOT NULL,"
								   + " id_hospital INTEGER,"
								   + " received[yes/no] BOOLEAN,"
								   //cuando hagamos el pojo declaramos boolean
								   + " FOREIGN KEY (id_hospital) REFERENCES hospital (id)"
								   + " ON UPDATE RESTRICT ON DELETE CASCADE)";				
						request.executeUpdate(sqlrequest);
						request.close();
				
				Statement organ = c.createStatement();
				String sqlorgan = "CREATE TABLE organ"
								+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
								+ " typeorgan TEXT NOT NULL,"
								+ " lifespan INTEGER NOT NULL,"
								+ " id_donor INTEGER NOT NULL,"
								+ " id_doctor INTEGER , "
								+ " id_req INTEGER ,"
								+ " FOREIGN KEY (id_donor) REFERENCES donor (id)"
								+ " ON UPDATE RESTRICT ON DELETE CASCADE,"
								+ " FOREIGN KEY (id_doctor) REFERENCES doctor (id) "
								+ " ON UPDATE RESTRICT ON DELETE CASCADE,"
								+ " FOREIGN KEY (id_req) REFERENCES request (id) "
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
										+ "PRIMARY KEY (id_doctor, id_hospital))";
				relationship.executeUpdate(sqlrelationship);
				relationship.close();
				
				Statement stmtSeq = c.createStatement();
				String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('donor', 1)";
				stmtSeq.executeUpdate(sqlSeq);
				stmtSeq.close();
				
				Statement stmtSeq1 = c.createStatement();
				String sqlSeq1 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('request', 1)";
				stmtSeq1.executeUpdate(sqlSeq1);
				stmtSeq1.close();
				
				Statement stmtSeq2 = c.createStatement();
				String sqlSeq2 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('organ', 1)";
				stmtSeq2.executeUpdate(sqlSeq2);
				stmtSeq2.close();
				
				Statement stmtSeq3 = c.createStatement();
				String sqlSeq3 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('hospital', 1)";
				stmtSeq3.executeUpdate(sqlSeq3);
				stmtSeq3.close();
				
				Statement stmtSeq4 = c.createStatement();
				String sqlSeq4 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('doctor', 1)";
				stmtSeq4.executeUpdate(sqlSeq4);
				stmtSeq4.close();
				
				
				//System.out.println("Database connection closed.");
			
				
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
		
		
		public void insertHosp( Hospital h){
			try{
				
				String sql = "INSERT INTO hospital (name, location) "
						+ "VALUES (?,?);";//each question mark will be replaced with a string
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setString(1, h.getName());
				prep.setString(2, h.getLocation());
				prep.executeUpdate();//without parameters because you already pass the parameters before
				prep.close();
				System.out.println("Hospital info processed");
				System.out.println("Record inserted.");
			
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		public List<Donor> getAllDonors() throws SQLException{
			Statement stmt = c.createStatement();	
			String sql = "SELECT * FROM Donor";
			ResultSet rs = stmt.executeQuery(sql);
			List<Donor> listDonor=new ArrayList<Donor>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date dateofbirth = rs.getDate("datebirth");
				String bloodType=rs.getString("bloodtype");
				String location=rs.getString("location");
				Donor d = new Donor(id, name, dateofbirth, bloodType, location);
				listDonor.add(d);
			}
			rs.close();
			stmt.close();
			return listDonor;
		}

//this makes you a list with all the doctors
public List<Doctor> getAllDoctors() throws SQLException {
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM doctor";
	ResultSet rs = stmt.executeQuery(sql);
	List<Doctor> list1= new ArrayList<Doctor>();
	while (rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String speciality = rs.getString("speciality");
		Doctor d = new Doctor(id, name, speciality);
		list1.add(d);
	}
	rs.close();
	stmt.close();
	return list1;
}

public void updateDoc(Doctor d) throws SQLException{
	
	String sql = "UPDATE doctor SET speciality=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, d.getSpeciality());
	prep.setInt(2, d.getId());
	prep.executeUpdate();
	
}	

public void deleteDoc(Doctor d) throws SQLException{
	
	String sql = "DELETE FROM doctor WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, d.getId());
	prep.executeUpdate();
}
public void deleteHospital(Hospital h) throws SQLException{
	
	String sql = "DELETE FROM hospital WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, h.getId());
	prep.executeUpdate();
}

//this makes you a list with all the hospitals

public List<Hospital> getAllHospitals() throws SQLException {
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM hospital";
	ResultSet rs = stmt.executeQuery(sql);
	List<Hospital> list1= new ArrayList<Hospital>();
	while (rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String location = rs.getString("location");
		Hospital h = new Hospital(id, name, location);
		list1.add(h);
	}
	rs.close();
	stmt.close();
	return list1;
}

public List<Request> getAllRequests() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM request";
	ResultSet rs = stmt.executeQuery(sql);
	List<Request> list1= new ArrayList<Request>();
	while (rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		Date dateOfbirth = rs.getDate("datebirth");
		String bloodType = rs.getString("bloodtype");
		String organNeeded = rs.getString("organneeded");
		int priority = rs.getInt("priority");
		boolean received=rs.getBoolean("received[yes/no]");
		Request r = new Request(id, name, dateOfbirth,bloodType,organNeeded,priority,received);
		list1.add(r);
	}
	rs.close();
	stmt.close();
	return list1;
}

public void updateHosp(Hospital h) throws SQLException{
	
	String sql = "UPDATE hospital SET name=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, h.getName());
	prep.setInt(2, h.getId());
	prep.executeUpdate();
	
}
public void insertOrgan( Organ o){
	try{
		
		String sql = "INSERT INTO organ (typeOforgan,lifeSpan, idDonor,idDoctor,idRequest) "
				+ "VALUES (?,?,?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, o.getTypeorgan());
		prep.setInt(2, o.getLifespan());
		prep.setInt(3, o.getDonor().getId());
		prep.setInt(4, o.getDoctor().getId());
		prep.setInt(5, o.getRequest().getId());
		prep.executeUpdate();//without parameters because you already pass the parameters before
		prep.close();
		System.out.println("Organ info processed");
		System.out.println("Record inserted.");
	
	}catch (Exception e){
		e.printStackTrace();
	}

}


public List<Doctor> searchDoctorByName (String dname) throws SQLException{
	
	String sql = "SELECT * FROM doctor WHERE name LIKE ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, "%" + dname + "%");
	ResultSet rs = prep.executeQuery();
	Doctor d=null;
	List<Doctor>list1=new ArrayList<Doctor>();
	while (rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String speciality = rs.getString("speciality");
		d = new Doctor (id, name, speciality);
		list1.add(d);
	}
	
	rs.close();
	prep.close();
	return list1;
	}
}
		



