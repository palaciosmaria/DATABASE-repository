package transplantation.pojo;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="donors")

public class Donor implements Serializable{
	
	
	private static final long serialVersionUID = 2233462938810735837L;
	
	@Id
	@GeneratedValue(generator = "donors")
	@TableGenerator(name = "donors", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "donors")
	
	//Atributes
	private Integer id;
	private String name;
	private Date datebirth;
	private String bloodtype;
	private String location;
	private List<Organ> organs;	
	
	//constructors
	public Donor() {
		super();
	}

	
	public Donor(Integer id, String name, Date datebirth, String bloodtype, String location, List<Organ> organs) {
		super();
		this.id = id;
		this.name = name;
		this.datebirth = datebirth;
		this.bloodtype = bloodtype;
		this.location = location;
		this.organs = organs;
	}


	public Donor(Integer id, String name, Date datebirth, String bloodtype, String location) {
		super();
		this.id = id;
		this.name = name;
		this.datebirth = datebirth;
		this.bloodtype = bloodtype;
		this.location = location;
	}

	public Donor(Integer id, String bloodtype, String location) {
		super();
		this.id = id;
		this.bloodtype = bloodtype;
		this.location = location;
	}

	//Hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	//equals for primary keys
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
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

	public Date getDatebirth() {
		return datebirth;
	}

	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	

	public List<Organ> getOrgans() {
		return organs;
	}


	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}


	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", datebirth=" + datebirth + ", bloodtype=" + bloodtype
				+ ", location=" + location + ", organs=" + organs + "]";
	}



	}
	
	
	//missing relations
	
	

	
	
	
	


