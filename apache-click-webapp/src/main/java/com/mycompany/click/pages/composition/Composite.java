package com.mycompany.click.pages.composition;

import org.apache.click.control.TextField;

import com.mycompany.click.pages.template.InnerBorderPage;

public class Composite extends InnerBorderPage {

	private static final long serialVersionUID = 1L;

	public SnippetOnePanel snippetOne;
	public SnippetOnePanel snippetTwo;
	public SnippetOnePanel snippetThree;
	public TextField text = new TextField();
	
	@Override
	public void onInit() {
		super.onInit();
		System.out.println(getClass() + " onInit");
		snippetOne = new SnippetOnePanel("snippetOne", new ICaller() {
			@Override
			public String getCaller() {
				return "first caller";
			}
			@Override
			public void called(String message) {
				text.setValue(message);
			}
		});
//		addModel("snippetOne", snippetOne);
//		snippetOne = new SnippetOnePanel(new Caller(text, "first caller"), "first snippet");
//		snippetTwo = new SnippetOnePanel(new Caller(text, "second caller"), "second snippet");
//		snippetThree = new SnippetOnePanel(new Caller(text, "third caller"), "third snippet");
	}
	
}
