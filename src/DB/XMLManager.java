package DB;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import transplantation.pojo.Donor;
import transplantation.pojo.Donor_List;



public class XMLManager {

	public void Marshall(Donor_List dns, String ruta) throws JAXBException {
		// Create the JAXBContext
				JAXBContext jaxbContext = JAXBContext.newInstance(Donor_List.class);
				// Get the marshaller
				Marshaller marshaller = jaxbContext.createMarshaller();
				
				// Pretty formatting
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
				File file = new File("./xmls/"+ruta);
				marshaller.marshal(dns, file);
	}
	
	public Donor_List Unmarshall(String ruta) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Donor_List.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		File file = new File("./xmls/"+ruta);
		Donor_List dns = (Donor_List)unmarshaller.unmarshal(file);
		return dns;
	}
}
