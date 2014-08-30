package com.mycompany.click.pages.composition;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Panel;

/**
 * 
 * @author ufo
 * 
 */
public class SnippetOnePanel extends Panel {

	private static final long serialVersionUID = 1L;

	public ActionLink actionLink = new ActionLink("selectAction");

	public SnippetOnePanel(String name, final ICaller caller) {
		super(name);
		actionLink.setValue(caller.getCaller());
		actionLink.setActionListener(new ActionListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean onAction(Control source) {
				System.out.println("click!!");
				caller.called("Called by ");
				return false;
			}
		});
		addModel("selectAction", actionLink);
	}

	// @Override
	// public void onInit() {
	// super.onInit();
	// System.out.println(getClass() + " onInit");
	// label.setName(caller.getCaller());
	// label.setActionListener(new ActionListener() {
	//
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public boolean onAction(Control source) {
	// System.out.println("click!!");
	// caller.called("Called by " + message);
	// return false;
	// }
	// });
	// addModel("label", label);
	// }

}
