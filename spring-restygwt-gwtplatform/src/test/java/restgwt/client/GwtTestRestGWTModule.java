package restgwt.client;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import restgwt.client.restygwt.RestHelloService;
import restgwt.client.restygwt.UserData;
import restgwt.client.restygwt.UserElaboratedData;
import restgwt.shared.FieldVerifier;
import restgwt.shared.RestURLPath;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase.
 * Using <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * 
 * If you run the tests using the Maven command line, you will have to
 * navigate with your browser to a specific url given by Maven.
 * See http://mojo.codehaus.org/gwt-maven-plugin/user-guide/testing.html
 * for details.
 */
public class GwtTestRestGWTModule extends GWTTestCase {

	/**
	 * Must refer to a valid module that sources this class.
	 */
	@Override
	public String getModuleName() {
		return "restgwt.RestGWTModuleJUnit";
	}

	/**
	 * Tests the FieldVerifier.
	 */
	public void testFieldVerifier() {
		assertFalse(FieldVerifier.isValidName(null));
		assertFalse(FieldVerifier.isValidName(""));
		assertFalse(FieldVerifier.isValidName("a"));
		assertFalse(FieldVerifier.isValidName("ab"));
		assertFalse(FieldVerifier.isValidName("abc"));
		assertTrue(FieldVerifier.isValidName("abcd"));
	}

	/**
	 * This test will send a request to the server using the greetServer method in
	 * GreetingService and verify the response.
	 */
	/**
	 * Uhm.. it doesnt' work... it seems that the server is not started
	 */
	public void testGreetingService() {
		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		System.out.println("GWT.getHostPageBaseURL(): " + GWT.getHostPageBaseURL());
		System.out.println("GWT.getModuleBaseURL(): " + GWT.getModuleBaseURL());
		System.out.println("GWT.getModuleName(): " + GWT.getModuleName());
		System.out.println("GWT.getModuleBaseForStaticFiles(): " + GWT.getModuleBaseForStaticFiles());
		System.out.println("GWT.getModuleBaseURL().replace(GWT.getModuleName() bla bla bla" +GWT.getModuleBaseURL().replace(GWT.getModuleName() + "/", "") + RestURLPath.BASE + RestURLPath.HELLO_CONTROLLER );

		RestHelloService.New.get().getInfoAsync( new UserData(2000, "ufo") , new MethodCallback<UserElaboratedData>() {
			@Override
			public void onSuccess(Method method, UserElaboratedData response) {
				GWT.log("Name received: [" + response.name + "]");
				assertTrue(response.name.contains("ufo"));
				finishTest();
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				GWT.log("Error", exception);
				fail("Request failure: " + exception.getMessage());
			}
		});
	}


}
