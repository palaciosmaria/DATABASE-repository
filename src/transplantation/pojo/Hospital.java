package transplantation.pojo;
import java.io.Serializable;

public class Hospital implements Serializable {

		
	private static final long serialVersionUID = -4466377494304259897L;
	
		//atributes
		private Integer id;
		private String name;
		private String location;
		
		
		//contructors
		public Hospital() {
			super();
		}


		public Hospital(Integer id, String name, String location) {
			super();
			this.id = id;
			this.name = name;
			this.location = location;
		}


		public Hospital(String name, String location) {
			super();
			this.name = name;
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


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Hospital other = (Hospital) obj;
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


		public String getLocation() {
			return location;
		}


		public void setLocation(String location) {
			this.location = location;
		}


		@Override
		public String toString() {
			return "Hospital [id=" + id + ", name=" + name + ", location=" + location + "]";
		}
	
		//missing relations
		
}
