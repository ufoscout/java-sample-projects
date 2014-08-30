package com.mycompany.helloworld;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mycompany.StatelessWebPage;

public class HelloWorldPage extends StatelessWebPage {

	private static final long serialVersionUID = 1L;

    public HelloWorldPage(final PageParameters parameters) {
    	setStatelessHint(true);
		add(new Label("message", "<b>Hello World!</b> (<script>are strings escaped by default?</script>)"));
    }

}
