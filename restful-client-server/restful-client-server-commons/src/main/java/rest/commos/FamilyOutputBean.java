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
public class FamilyOutputBean {

	private String format;
	private boolean isFather;
	private List<String> description = new ArrayList<String>();
	
	public boolean isFather() {
		return isFather;
	}
	public void setFather(boolean isFather) {
		this.isFather = isFather;
	}
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
}
