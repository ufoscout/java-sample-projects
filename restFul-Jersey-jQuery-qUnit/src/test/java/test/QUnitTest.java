package test;

import static org.junit.Assert.*;

import org.junit.Before;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QUnitTest {

	private static String testWebAppPath = "src/test/webapp";
	private static String testHtmlDir = "test";
	private File testFile;
	private EmbeddedJettyServer server;

	public QUnitTest(File testFile) {
		super();
		this.testFile = testFile;
	}

	@Before
	public void setUp() throws Exception {
		server = EmbeddedJettyServer.instance();
	}

	@Parameters
	public static Collection<Object[]> testFiles() {
		ArrayList<Object[]> files = new ArrayList<Object[]>();
		File qunitDir = new File(testWebAppPath + "/" + testHtmlDir);
		for (File file : qunitDir.listFiles()) {
			if (file.getName().endsWith(".html")) {
				files.add(new Object[] { file });
			}
		}
		return files;
	}

	@Test
	public void runQUnitTests() throws Exception {
		WebClient client = new WebClient();
		client.setJavaScriptEnabled(true);

		System.out.println("Testing: " + server.getBaseWebUrl() + "/" + testHtmlDir + "/" + testFile.getName());
		HtmlPage page = client.getPage(server.getBaseWebUrl() + "/" + testHtmlDir + "/" + testFile.getName());

		// try 20 times to wait .5 second each for filling the page.
		HtmlElement element = null;
		for (int i = 0; i < 20; ++i) {
			try {
				element = page.getHtmlElementById("qunit-testresult");
				break;
			} catch (ElementNotFoundException e) {
				synchronized (page) {
					System.out.println("Waiting for JavaScript tests...");
					page.wait(500);
				}
			}
		}
		System.out.println(element.getTextContent());
		assertTrue(element.getTextContent().contains(", 0 failed."));
	}
}
