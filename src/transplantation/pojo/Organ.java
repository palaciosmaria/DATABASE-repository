package transplantation.pojo;
import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name= "organ")


public class Organ implements Serializable {
	
	private static final long serialVersionUID = -6561993325465307742L;
	
	@Id
	@GeneratedValue(generator="organ")
	@TableGenerator(name="organ",table="sqlite_sequence",pkColumnName="name",
	valueColumnName="seq", pkColumnValue="organ")
	
	
	

	
	
	private Integer id;
	
	
	private String typeorgan;
	
	
	private Integer lifespan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="id_donor")
	
	private Donor id_donor;
	
	@OneToOne (fetch=FetchType.LAZY)
	@JoinColumn (name="id_request")
	
	private Request id_request;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="id_doctor")
	
	private Doctor id_doctor;
	
	
	
//Constructors	
	public Organ() {
		super();
	}


	public Organ(Integer id, String typeorgan, Integer lifespan, Donor donor, Request request, Doctor doctor) {
	super();
	this.id = id;
	this.typeorgan = typeorgan;
	this.lifespan = lifespan;
	this.id_donor = donor;
	this.id_request = request;
	this.id_doctor = doctor;
}
	public Organ(String typeorgan, Integer lifespan, Donor donor) {
		super();
		
		this.typeorgan = typeorgan;
		this.lifespan = lifespan;
		this.id_donor = donor;
		
	}

	public Organ(String typeorgan, Integer lifespan, Donor donor,Request request, Doctor doctor) {
		super();
		
		this.typeorgan = typeorgan;
		this.lifespan = lifespan;
		this.id_donor = donor;
		this.id_request = request;
		this.id_doctor = doctor;
	}


	public Organ(Integer id, String typeorgan) {
		super();
		this.id = id;
		this.typeorgan = typeorgan;
	}

public Organ(String typeOforgan, Integer lifeSpan2, Integer donorId, Integer doctorId, Integer requestId) {
		// TODO Auto-generated constructor stub
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
		return id_donor;
	}


	public void setDonor(Donor donor) {
		this.id_donor = donor;
	}


	public Request getRequest() {
		return id_request;
	}


	public void setRequest(Request request) {
		this.id_request = request;
	}


	public Doctor getDoctor() {
		return id_doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.id_doctor = doctor;
	}


	public String toStringComplete() {
		return "Organ [id=" + id + ", typeorgan=" + typeorgan + ", lifespan=" + lifespan + ", donor=" + id_donor.getId()
				+ ", request=" + id_request.getId() + ", doctor=" + id_doctor.getId() + "]";
	}


	@Override
	public String toString() {
		return "Organ [id=" + id + ", typeorgan=" + typeorgan + ", lifespan=" + lifespan + "]";
	}
}
