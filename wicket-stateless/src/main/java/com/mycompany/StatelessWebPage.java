package com.mycompany;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.exception.NotStatelessException;

/**
 * An abstract stateless page that, if the mode is deployment, checks that
 * the current page is stateless
 * @author ufo
 *
 */
public abstract class StatelessWebPage extends WebPage {

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		if (getApplication().getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT)){
			logger.debug("Begin checking if page [{}] is stateless", getClass().getSimpleName());
			checkIfPageStateless(this);
		}
	}
	
	private void checkIfPageStateless(Page p) {
		if (!p.isPageStateless()) {
			final List<Component> statefulComponents = new ArrayList<Component>();
			
			p.visitChildren(Component.class, new IVisitor<Component, Component>() {
				@Override
				public void component(Component component, IVisit<Component> visit) {
					if (!component.isStateless()) {
						statefulComponents.add(component);
					}
				}
			});
 
			StringBuffer message = new StringBuffer("This page is no longer stateless due to the following stateful components:");
			if (statefulComponents.size() > 0) {
				for (Component c : statefulComponents) {
					message.append( Strings.LINE_SEPARATOR );
					message.append( c.getMarkupId() );
					logger.error("Component [{}] of page [{}] is not stateless!", new Object[]{c.getMarkupId(), getClass().getSimpleName()});
				}
			}
			throw new NotStatelessException(message.toString());
		}
	}
}
