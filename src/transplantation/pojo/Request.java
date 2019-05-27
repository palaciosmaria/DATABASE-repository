package transplantation.pojo;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name="request")

public class Request implements Serializable  {

	
	private static final long serialVersionUID = -5410714361732009493L;
	
	@Id
	@GeneratedValue(generator= "request")
	@TableGenerator(name = "request", table = "sqlite_sequence",
	pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "request")
	//atributes
	private Integer id;
	private String name;
	private Date datebirth;
	private String bloodtype;
	private String organneeded;
	private Integer priority;
	private Boolean received;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_hospital")
	private Hospital id_hospital;
	@OneToOne (fetch=FetchType.LAZY, mappedBy="id_request")
	private Organ organ;
	
	
	//constructors
	public Request() {
		super();
	}
	
	

	public Request(String name, Date datebirth, String bloodtype, String organneeded, Integer priority) {
		super();
		this.name = name;
		this.datebirth = datebirth;
		this.bloodtype = bloodtype;
		this.organneeded = organneeded;
		this.priority = priority;
	}



	public Request(Integer id, String name, Date datebirth, String bloodtype, String organneeded, Integer priority) {
		super();
		this.id = id;
		this.name = name;
		this.datebirth = datebirth;
		this.bloodtype = bloodtype;
		this.organneeded = organneeded;
		this.priority = priority;
		
	}

	public Request(Integer id, String name, Date datebirth, String bloodtype, String organneeded, Integer priority, Boolean received, Hospital hospital, Organ organ) {
		super();
		this.id = id;
		this.name = name;
		this.datebirth = datebirth;
		this.bloodtype = bloodtype;
		this.organneeded = organneeded;
		this.priority = priority;
		this.received= received;
		this.organ = organ;
		this.id_hospital = hospital;
	}

	public Request(Integer id, String bloodtype, String organneeded, Integer priority, Hospital hospital) {
		super();
		this.id = id;
		this.bloodtype = bloodtype;
		this.organneeded = organneeded;
		this.priority = priority;
		this.id_hospital = hospital;
		
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
		Request other = (Request) obj;
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

	public String getorganneeded() {
		return organneeded;
	}

	public void setorganneeded(String organneeded) {
		this.organneeded = organneeded;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getReceived() {
		return received;
	}

	public void setReceived(Boolean received) {
		this.received = received;
	}

	public Hospital getHospital() {
		return id_hospital;
	}

	public void setHospital(Hospital hospital) {
		this.id_hospital = hospital;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", name=" + name + ", datebirth=" + datebirth + ", bloodtype=" + bloodtype
				+ ",\n       "+ " organneeded=" + organneeded  + ", priority=" + priority + ", received=" + received + ", hospital="
				+ id_hospital + ", organ=" + organ + "]\n";
	}
	
	
	
	
	
}
