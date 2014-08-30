package rest.commos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Francesco Cina'
 *
 * 30 May 2012
 */
@XmlRootElement
public class FamilyInputBean {

	private String firstName;
	private String secondName;
	private int repeat;
	private final List<String> children = new ArrayList<String>();
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public int getRepeat() {
		return repeat;
	}
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	public List<String> getChildren() {
		return children;
	}
	
}
