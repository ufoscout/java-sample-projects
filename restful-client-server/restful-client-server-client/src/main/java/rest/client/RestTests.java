package rest.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rest.commos.Constants;
import rest.commos.FamilyDescriptionGenerator;
import rest.commos.FamilyInputBean;
import rest.commos.FamilyOutputBean;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author Francesco Cina'
 * 
 *         30 May 2012
 */
public class RestTests extends BaseRestTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHelloWorld_GET() {
		WebResource webResource = getWebResource();
		String message = webResource.path(Constants.URI_HELLOWORLD).get(String.class);
		System.out.println("Received message from [" + Constants.URI_HELLOWORLD + "] = [" + message + "]");
		assertEquals(Constants.HELLOWORLD_MESSAGE, message);
	}

	@Test
	public void testMathematicsAdd_GET_WithQueryString() {
		Random random = new Random();
		Integer valueOne = random.nextInt(1000);
		Integer valueTwo = random.nextInt(1000);
		
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("valueOne", valueOne.toString());
		queryParams.add("valueTwo", valueTwo.toString());
		
		WebResource webResource = getWebResource();
		
		Integer sum = Integer.valueOf(webResource.path(Constants.URI_MATHEMATICS + Constants.URI_MATHEMATICS_ADD).queryParams(queryParams).get(String.class));
		
		System.out.println("Received sum from [" + Constants.URI_MATHEMATICS+ Constants.URI_MATHEMATICS_ADD + "] = [" + sum + "]");
		System.out.println("Expected sum: [" + (valueOne + valueTwo) + "]");
		
		assertEquals( (valueOne + valueTwo) , sum.intValue() );
	}
	
	@Test
	public void testMathematicsMultiply_POST_WithFormData() {
		Random random = new Random();
		Integer valueOne = random.nextInt(1000);
		Integer valueTwo = random.nextInt(1000);
		
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("valueOne", valueOne.toString());
		formData.add("valueTwo", valueTwo.toString());
		
		WebResource webResource = getWebResource();
		
		Integer product = Integer.valueOf(webResource.path(Constants.URI_MATHEMATICS + Constants.URI_MATHEMATICS_MULTIPLY).post(String.class, formData));
		
		System.out.println("Received product from [" + Constants.URI_MATHEMATICS+ Constants.URI_MATHEMATICS_MULTIPLY + "] = [" + product + "]");
		System.out.println("Expected product: [" + (valueOne * valueTwo) + "]");
		
		assertEquals( (valueOne * valueTwo) , product.intValue() );
	}
	
	@Test
	public void testFamily_POST_XML() {
		FamilyInputBean familyInputBean = new FamilyInputBean();
		
		String firstName = "Jack";
		familyInputBean.setFirstName(firstName);
		
		String secondName = "Frusciante";
		familyInputBean.setSecondName(secondName);
		
		int repeat = 1;
		familyInputBean.setRepeat(repeat);
		
		ClientResponse clientResponse = getWebResource().path(Constants.URI_FAMILY).type(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, familyInputBean);
		System.out.println("clientResponse status code: [" + clientResponse.getStatus() + "]");
		assertEquals(200 , clientResponse.getStatus());
		
		FamilyOutputBean familyOutputBean = clientResponse.getEntity(FamilyOutputBean.class);
		assertNotNull(familyOutputBean);
		
		assertFalse( familyOutputBean.isFather() );
		assertEquals( repeat , familyOutputBean.getDescription().size() );
		assertEquals( "XML" , familyOutputBean.getFormat() );
		
		System.out.println("XML received description: [" + familyOutputBean.getDescription().get(0) + "]");
		String expecteDescription = FamilyDescriptionGenerator.describe(familyInputBean.getFirstName(), familyInputBean.getSecondName(), familyInputBean.getChildren());
		System.out.println("Expected description     : [" + expecteDescription + "]");
		
		for ( String description : familyOutputBean.getDescription() ) {
			assertEquals( expecteDescription , description );
		}
	}
	
	@Test
	public void testFamily_POST_JSON() {
		FamilyInputBean familyInputBean = new FamilyInputBean();
		
		String firstName = "Alan";
		familyInputBean.setFirstName(firstName);
		
		String secondName = "Parson";
		familyInputBean.setSecondName(secondName);
		
		int repeat = 7;
		familyInputBean.setRepeat(repeat);
		
		Collection<String> children = new ArrayList<String>();
		children.add("John");
		children.add("Mike");
		children.add("Lucy");
		familyInputBean.getChildren().addAll(children);
		
		ClientResponse clientResponse = getWebResource().path(Constants.URI_FAMILY).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, familyInputBean);
		System.out.println("clientResponse status code: [" + clientResponse.getStatus() + "]");
		assertEquals(200 , clientResponse.getStatus());
		
		FamilyOutputBean familyOutputBean = clientResponse.getEntity(FamilyOutputBean.class);
		assertNotNull(familyOutputBean);
		
		assertTrue( familyOutputBean.isFather() );
		assertEquals( repeat , familyOutputBean.getDescription().size() );
		assertEquals( "JSON" , familyOutputBean.getFormat() );
		
		System.out.println("JSON received description: [" + familyOutputBean.getDescription().get(0) + "]");
		String expecteDescription = FamilyDescriptionGenerator.describe(familyInputBean.getFirstName(), familyInputBean.getSecondName(), familyInputBean.getChildren());
		System.out.println("Expected description     : [" + expecteDescription + "]");
		
		for ( String description : familyOutputBean.getDescription() ) {
			assertEquals( expecteDescription , description );
		}
	}
	
}
