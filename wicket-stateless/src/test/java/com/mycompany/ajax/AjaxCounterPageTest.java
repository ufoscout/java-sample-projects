package com.mycompany.ajax;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Test;

import com.mycompany.BaseWicketTest;

public class AjaxCounterPageTest extends BaseWicketTest {

	@Test
	public void testStateless() {
		checkForStateless(new AjaxCounterPage(new PageParameters()) );
	}

	@Test
	public void testRender() {
		checkRenderPage(AjaxCounterPage.class);
	}
}
