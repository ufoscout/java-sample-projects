package groovy.simple.web.test

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import geb.Browser;
import geb.spock.GebReportingSpec
import geb.spock.GebSpec;
import groovy.simple.EmbeddedJettyServer;
import groovy.simple.EmbeddedTomcatServer;
import groovy.simple.Util;
import groovy.simple.web.page.IndexPage;


class IndexPageTest extends GebSpec {

	def "User should see the application name in the index page"() {

		println 'Start test'

//		def browser = new Browser(driver: new FirefoxDriver());
		def browser = new Browser(driver: new HtmlUnitDriver());
		def baseUrl = EmbeddedTomcatServer.instance().getBaseWebUrl() + "/";
		println(baseUrl);
		browser.setBaseUrl(baseUrl);
		
		set_browser(browser)

		reportGroup 'See index page'

		println 'pre browse drive'

		when: 'a user access the system'
		to IndexPage

		println 'pre then clause'
		
		then:
		at IndexPage
		
//		and:
//		applicationNameSpan.text() == Util.TEST_GROOVY_PAGE

//		when:
//		Browser.drive() {
//			
//			setBaseUrl(EmbeddedJettyServer.instance().getBaseWebUrl())
//			
//			//login
//			when: 'a user access the system'
//			to IndexPage
//
//			then:
//			applicationNameSpan.text() == Util.TEST_GROOVY_PAGE
//		}.quit()
//
//		then:
//		true

		println 'post browse drive'
	}
}
