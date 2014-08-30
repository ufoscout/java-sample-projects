package com.mycompany;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class HomePageTest extends BaseWicketTest
{

	@Test
	public void testStateless()
	{
		checkForStateless(new HomePage(new PageParameters()));
	}
	
	@Test
	public void testRender()
	{
		checkRenderPage(HomePage.class);
	}
	
}
