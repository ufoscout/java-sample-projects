package com.mycompany.click.pages.form;

import java.util.Random;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;

import com.mycompany.click.pages.template.InnerBorderPage;

/**
 * 
 * @author ufo
 *
 */
public class ProgrammaticFormPage extends InnerBorderPage {

	private static final long serialVersionUID = 1L;

	public Form form = new Form();
	public ActionLink generateNameLink = new ActionLink();
	
    private TextField nameField;

    public ProgrammaticFormPage() {

        form.setLabelsPosition(Form.POSITION_TOP);

        nameField = new TextField("Enter your name");

        form.add(nameField);
        form.add(new Submit("Submit", this, "onSubmitClick"));
        
        generateNameLink.setActionListener(new ActionListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean onAction(Control source) {
				nameField.setValue(getRandomName());
				return true;
			}
		});

    }
    
    public boolean onSubmitClick()
    {
        ProgrammaticFormSayHelloPage nextPage = getContext().createPage(ProgrammaticFormSayHelloPage.class);
        nextPage.setName(nameField.getValue());
        
        setForward(nextPage);
        
        return false;
    }

    private String[] names = new String[]{"Luke Skywalker", "Roberto Baggio", "Leonardo DaVinci",
    		"Francesco Cina", "M.Schumacher", "Airton Senna", "Giuseppe Signori"};
    
    private String getRandomName() {
    	return names[ new Random().nextInt(names.length) ];
    }
}
