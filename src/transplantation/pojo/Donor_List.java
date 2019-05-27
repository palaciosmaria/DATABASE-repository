package transplantation.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Donor_List")
public class Donor_List {
@XmlElement(name="Donor")
private List<Donor> listDonor;


public Donor_List() {
	super();
}

public Donor_List(List<Donor> listDonor) {
	super();
	this.listDonor = listDonor;
}

public List<Donor> getListDonor() {
	return listDonor;
}

public void setListDonor(List<Donor> listDonor) {
	this.listDonor = listDonor;
}


@Override
public String toString() {
	return "Donor_List [listDonor=" + listDonor + "]";
}







}
