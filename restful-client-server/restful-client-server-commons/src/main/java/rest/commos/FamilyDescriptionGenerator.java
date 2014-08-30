package rest.commos;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 30 May 2012
 */
public class FamilyDescriptionGenerator {

	public static String describe(String firstName, String secondName, List<String> children){
		StringBuilder description = new StringBuilder();
		description.append("Mr. ");
		description.append(firstName);
		description.append(" ");
		description.append(secondName);
		if (children.isEmpty()) {
			description.append(" has no children.");
		} else {
			description.append(" is the father of ");
			description.append( Arrays.toString(children.toArray()) );
		}
		return description.toString();
	}
}
