package com.mycompany;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mycompany.ajax.AjaxCounterPage;
import com.mycompany.helloworld.HelloWorldPage;

public class HomePage extends StatelessWebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	
    	setStatelessHint(true);
    	
		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
		add(new BookmarkablePageLink<HelloWorldPage>("helloWorldPageLink", HelloWorldPage.class));
		add(new BookmarkablePageLink<HelloWorldPage>("ajaxCounterPageLink", AjaxCounterPage.class));
    }
}
