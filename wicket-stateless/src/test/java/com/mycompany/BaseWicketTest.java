package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.util.tester.WicketTester;
import static org.junit.Assert.*;
import org.junit.Before;

public abstract class BaseWicketTest {

	private WicketTester tester;

	@Before
	public void setUpBaseTest() {
		setTester(new WicketTester(new WicketApplication()));
	}

	public WicketTester getTester() {
		return tester;
	}

	public void setTester(WicketTester tester) {
		this.tester = tester;
	}

	protected void checkForStateless(Page page) {
		if (!page.isPageStateless()) {
			// find the reason
			Component statefulComponent = page.visitChildren(Component.class, new StatelessCheckerVisitor());
			if (statefulComponent != null) {
				fail("Stateless page contains stateful component ["
						+ statefulComponent.getClass().getName() + " : "
						+ statefulComponent.getMarkupId() + "]");
			}
		}
	}
	
	protected <T extends Page> void checkRenderPage(Class<T> page){
		//start and render the test page
		WicketTester testerLocal = getTester();
		testerLocal.startPage(page);
		//assert rendered page class
		testerLocal.assertRenderedPage(page);
	}
}
