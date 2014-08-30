package com.mycompany.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.code.joliratools.StatelessAjaxFallbackLink;
import com.mycompany.StatelessWebPage;

public class AjaxCounterPage extends StatelessWebPage {

	private static final long serialVersionUID = 1L;

	public AjaxCounterPage(PageParameters pageParameters) {
		final Model<Integer> model = new Model<Integer>() {
			private static final long serialVersionUID = 1L;
			private int counter = 0;
	
	        public Integer getObject() {
	            return counter++;
	        }
	    };
	    final Label label = new Label("counter", model);
        label.setOutputMarkupId(true);
        
        add(new StatelessAjaxFallbackLink<Integer>("link") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
                if (target != null) {
                    // target is only available in an Ajax request
                    target.add(label);
                }
			}
        });
        add(label);
	}
}
