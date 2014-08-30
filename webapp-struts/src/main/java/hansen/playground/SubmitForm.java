package hansen.playground;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

public final class SubmitForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String lastname = "Hansen"; // default value
	private String address = null;
	private String sex = null;
	private String married = null;
	private String age = null;
	
	public SubmitForm() {
		System.out.println("Creato SubmitForm");
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	  // Log the forms data
	  servlet.log("Lastname:" + lastname);       
	  servlet.log("Address:" + address);       
	  servlet.log("Sex:" + sex);       
	  servlet.log("Married:" + married);       
	  servlet.log("Age:" + age);
	  
	  // Check for mandatory data
	  ActionErrors errors = new ActionErrors();
	  if (lastname == null || lastname.equals("")) { 
	    errors.add("Last Name", new ActionMessage("error.lastname"));
	  }
	  if (address == null || address.equals("")) { 
	    errors.add("Address", new ActionMessage("error.address"));
	  }
	  if (sex == null || sex.equals("")) { 
	    errors.add("Sex", new ActionMessage("error.sex"));
	  }
	  if (age == null || age.equals("")) { 
	    errors.add("Age", new ActionMessage("error.age"));
	  }
	  return errors;
	}
	
}