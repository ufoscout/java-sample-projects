package com.mycompany.helloworld;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Test;

import com.mycompany.BaseWicketTest;

public class HelloWorldPageTest extends BaseWicketTest {

	@Test
	public void testStateless()
	{
		checkForStateless(new HelloWorldPage(new PageParameters()));
	}
	
	@Test
	public void testRender()
	{
		checkRenderPage(HelloWorldPage.class);
	}
}
