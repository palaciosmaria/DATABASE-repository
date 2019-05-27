package transplantation.pojo;
import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name= "organ")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Organ")
public class Organ implements Serializable {
	
	private static final long serialVersionUID = -6561993325465307742L;
	
	@Id
	@GeneratedValue(generator="organ")
	@TableGenerator(name="organ",table="sqlite_sequence",pkColumnName="name",
	valueColumnName="seq", pkColumnValue="organ")
	
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String typeorgan;
	@XmlAttribute
	private Integer lifespan;
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="id_donor")
	@XmlElement
	private Donor id_donor;
	@OneToOne (fetch=FetchType.LAZY)
	@JoinColumn (name="id_request")
	@XmlTransient
	private Request id_request;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="id_doctor")
	@XmlTransient
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
	
	public Organ(String typeorgan, Integer lifespan, Donor donor, Doctor doctor, Request request) {
		super();
		this.typeorgan = typeorgan;
		this.lifespan = lifespan;
		this.id_donor = donor;
		this.id_doctor = doctor;
		this.id_request = request;
		
	}
	public Organ(String typeorgan, Integer lifespan, Donor donor, Doctor doctor) {
		super();
		this.typeorgan = typeorgan;
		this.lifespan = lifespan;
		this.id_donor = donor;
		this.id_doctor = doctor;
		
	}
	public Organ(String typeorgan, Integer lifespan, Donor donor) {
		super();
		
		this.typeorgan = typeorgan;
		this.lifespan = lifespan;
		this.id_donor = donor;
		
	}
	public Organ(Integer id, String typeorgan, Integer lifespan) {
		super();
		this.id= id;
		this.typeorgan = typeorgan;
		this.lifespan = lifespan;
	
		
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
				+ ", request=" + id_request.getId() + "]";
	}



	
	


	
	@Override
	public String toString() {
		return "Organ [id=" + id + ", typeorgan=" + typeorgan + ", lifespan=" + lifespan + "]";
	}
	
}
