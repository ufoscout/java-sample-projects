package com.mycompany.click.pages;

import com.mycompany.click.pages.composition.Composite;
import com.mycompany.click.pages.form.ProgrammaticFormPage;
import com.mycompany.click.pages.form.ProgrammaticFormErrorPage;
import com.mycompany.click.pages.form.QueryParameterBindingPage;
import com.mycompany.click.pages.template.BorderPage;
import java.util.Date;

import org.apache.click.control.PageLink;

/**
 * 
 * @author ufo
 */
public class HomePage extends BorderPage {
    
	private static final long serialVersionUID = 1L;
	public String title = "Home";
    public Date currentTime = new Date();
    
    public PageLink programmaticForm = new PageLink(ProgrammaticFormPage.class);
    public PageLink programmaticFormTwo = new PageLink(ProgrammaticFormErrorPage.class);
    public PageLink queryParameterBinding = new PageLink(QueryParameterBindingPage.class);
    public PageLink composite = new PageLink(Composite.class);
    
}
