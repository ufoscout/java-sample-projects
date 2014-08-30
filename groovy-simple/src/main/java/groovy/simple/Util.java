package groovy.simple;

public class Util {

	public static String TEST_GROOVY_PAGE = "TEST GROOVY PAGE"; 
	
	public static String applicationName() {
		System.out.println("Util.applicationName() called");
		return TEST_GROOVY_PAGE;
	}
	
}
