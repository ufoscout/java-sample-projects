package hansen.playground;

import javax.servlet.http.*;
import org.apache.struts.action.*;

public final class SubmitAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println("--------- Chiamato SubmitAction ------");
		
		SubmitForm f = (SubmitForm) form; // get the form bean
		// and take the last name value
		String lastName = f.getLastname();
		// Translate the name to upper case and save it in the request object
		request.setAttribute("lastName", lastName.toUpperCase());

		// Forward control to the specified success target
		return (mapping.findForward("success"));
	}
}