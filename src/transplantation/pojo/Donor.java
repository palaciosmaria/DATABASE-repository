package transplantation.pojo;
import java.io.Serializable;
import java.sql.Date;

public class Donor implements Serializable{
	
	
	private static final long serialVersionUID = 2233462938810735837L;
	
	//Atributes
	private Integer id;
	private String name;
	private Date datebirth;
	private String bloodtype;
	private String location;
		
	//constructors
	public Donor() {
		super();
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

	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", datebirth=" + datebirth + ", bloodtype=" + bloodtype
				+ ", location=" + location + "]";
	}
	
	
	//missing relations
	
	

	
	
	
	

}
