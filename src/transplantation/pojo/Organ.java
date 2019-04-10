package transplantation.pojo;
import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name= "organ")

public class Organ implements Serializable {
	
	private static final long serialVersionUID = -6561993325465307742L;
	
	@Id
	@GeneratedValue(generator="organs")
	@TableGenerator(name="organs",table="sqlite_sequence",pkColumnName="name",
	valueColumnName="seq", pkColumnValue="organs")
	
	

	
	
	private Integer id;
	private String typeorgan;
	private Integer lifespan;
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="id_donor")
	private Donor donor;
	private Request request;
	private Doctor doctor;
	
	
	
//Constructors	
	public Organ() {
		super();
	}


	public Organ(Integer id, String typeorgan, Integer lifespan, Donor donor, Request request, Doctor doctor) {
	super();
	this.id = id;
	this.typeorgan = typeorgan;
	this.lifespan = lifespan;
	this.donor = donor;
	this.request = request;
	this.doctor = doctor;
}


	public Organ(Integer id, String typeorgan, Integer lifespan) {
		super();
		this.id = id;
		this.typeorgan = typeorgan;
		this.lifespan = lifespan;
	}


	public Organ(Integer id, String typeorgan) {
		super();
		this.id = id;
		this.typeorgan = typeorgan;
	}

// Hashcode 
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
		Organ other = (Organ) obj;
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


	public String getTypeorgan() {
		return typeorgan;
	}


	public void setTypeorgan(String typeorgan) {
		this.typeorgan = typeorgan;
	}


	public Integer getLifespan() {
		return lifespan;
	}


	public void setLifespan(Integer lifespan) {
		this.lifespan = lifespan;
	}

	public Donor getDonor() {
		return donor;
	}


	public void setDonor(Donor donor) {
		this.donor = donor;
	}


	public Request getRequest() {
		return request;
	}


	public void setRequest(Request request) {
		this.request = request;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	@Override
	public String toString() {
		return "Organ [id=" + id + ", typeorgan=" + typeorgan + ", lifespan=" + lifespan + ", donor=" + donor
				+ ", request=" + request + ", doctor=" + doctor + "]";
	}



	
	


	
}
