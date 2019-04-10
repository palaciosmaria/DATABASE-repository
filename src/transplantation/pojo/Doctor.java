package transplantation.pojo;
import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;




@Entity	
@Table(name= "doctors")
public class Doctor implements Serializable{
	

	private static final long serialVersionUID = -5979925139596330522L;
	
	@Id
	@GeneratedValue(generator = "employees")
	@TableGenerator(name = "employees", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "employees")
	
	//atributes
	private Integer id;
	private String name;
	private String speciality;
	
	private List<Organ> organs;
	private List<Hospital>hospitals;
	
	
	//constructors
	public Doctor() {
		super();
		this.organs = new ArrayList<Organ>();
		this.hospitals = new ArrayList<Hospital>();
		
	}


	public Doctor(Integer id, String name, String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.speciality = speciality;
	}

	public Doctor(Integer id, String speciality) {
		super();
		this.id = id;
		this.speciality = speciality;
		
		
	}
	
	public Doctor(String name, String speciality) {
		super();
		this.name = name;
		this.speciality = speciality;
	}
	
	public Doctor(Integer id) {
		super();
		this.id = id;
		
	}

	//Hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSpeciality() {
		return speciality;
	}


	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}


	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", speciality=" + speciality + "]";
	}


	public List<Hospital> getHospitals() {
		return hospitals;
	}


	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}


	public List<Organ> getOrgans() {
		return organs;
	}


	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}


	
	
	
	//missing relations

	
	
}
