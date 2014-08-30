package com.mycompany.click.pages.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.util.Bindable;

import com.mycompany.click.pages.template.InnerBorderPage;

/**
 * 
 * @author ufo
 *
 */
public class QueryParameterBindingPage extends InnerBorderPage{

	private static final long serialVersionUID = 1L;

	public Form form = new Form();

	//public fields are automatically bound
	public String name = "";
	@Bindable private String surname = "";

	private TextField nameField;
	private TextField surnameField;
	
	@Override
	public void onInit() {
		super.onInit();
		
        nameField = new TextField("name:");
        surnameField = new TextField("surname:");

        form.add(nameField);
        form.add(surnameField);
        
        form.add(new Submit("Submit", this, "onSubmitClick"));
		
	}
	
    public boolean onSubmitClick()
    {
//    	QueryParameterBindingPage nextPage = getContext().getPagePath(QueryParameterBindingPage.class);
//        nextPage.setP
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", nameField.getValue());
        params.put("surname", surnameField.getValue());
		setRedirect(getClass(), params);
        
        return false;
    }
}
