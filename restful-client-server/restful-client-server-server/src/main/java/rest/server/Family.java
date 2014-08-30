package rest.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rest.commos.Constants;
import rest.commos.FamilyDescriptionGenerator;
import rest.commos.FamilyInputBean;
import rest.commos.FamilyOutputBean;

@Path(Constants.URI_FAMILY)
public class Family {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FamilyOutputBean fatherDescriptionJson(FamilyInputBean familyInputBean) {
		FamilyOutputBean familyOutputBean = new FamilyOutputBean();
		familyOutputBean.setFormat("JSON");
		familyOutputBean.setFather( !familyInputBean.getChildren().isEmpty() );
		
		String description =  FamilyDescriptionGenerator.describe(familyInputBean.getFirstName(), familyInputBean.getSecondName(), familyInputBean.getChildren());
		
		for ( int i=0; i<familyInputBean.getRepeat(); i++) {
			familyOutputBean.getDescription().add(description);
		}
		
		return familyOutputBean;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public FamilyOutputBean fatherDescriptionXml(FamilyInputBean familyInputBean) {
		FamilyOutputBean familyOutputBean = new FamilyOutputBean();
		familyOutputBean.setFormat("XML");
		familyOutputBean.setFather( !familyInputBean.getChildren().isEmpty() );
		
		String description =  FamilyDescriptionGenerator.describe(familyInputBean.getFirstName(), familyInputBean.getSecondName(), familyInputBean.getChildren());
		
		for ( int i=0; i<familyInputBean.getRepeat(); i++) {
			familyOutputBean.getDescription().add(description);
		}
		
		return familyOutputBean;
	}

}
