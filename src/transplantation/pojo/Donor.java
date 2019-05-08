package transplantation.pojo;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;



@Entity
@Table(name="donor")

public class Donor implements Serializable{
	
	
	private static final long serialVersionUID = 2233462938810735837L;
	
	@Id
	@GeneratedValue(generator = "donor")
	@TableGenerator(name = "donor", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "donor")
	
	//Atributes
	private Integer id;
	private String name;
	private Date datebirth;
	private String bloodtype;
	private String location;

	@OneToMany(mappedBy= "id_donor")

	private List<Organ> organs;	
	
	//constructor
	public Donor() {
		super();
		this.organs= new ArrayList<Organ>();
	}

	
	public Donor(String name, Date datebirth, String bloodtype, String location) {
		super();
		this.name = name;
		this.datebirth = datebirth;
		this.bloodtype = bloodtype;
		this.location = location;
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


	public Donor(Integer id, String name, Date dateofbirth, String bloodtype, String location) {
		super();
		this.id = id;
		this.name = name;
		this.datebirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.location = location;
	}




	public Donor(Integer id, String bloodtype, String location) {
		super();
		this.id = id;
		this.bloodtype = bloodtype;
		this.location = location;
		this.organs = new ArrayList<Organ>(); 
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
	
	
	// Additional method to add to a list
	public void addOrgan(Organ organ) {
		if (!organs.contains(organ)) {
			this.organs.add(organ);
		}
	}
	
	// Additional method to remove from a list
	public void removeOrgan(Organ organ) {
		if (organs.contains(organ)) {
			this.organs.remove(organ);
		}
	}

	}
	
	
	
	
	

	
	
	
	


