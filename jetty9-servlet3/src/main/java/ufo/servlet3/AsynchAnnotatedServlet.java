package ufo.servlet3;

import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/asynchOne"}, asyncSupported=true)
public class AsynchAnnotatedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    @Override
    public void init() {
    	System.out.println("-------------------------------------");
        System.out.println(getClass().getSimpleName() + " started");
        System.out.println("-------------------------------------");
    }

    @Override
    public void destroy() {
    	System.out.println("-------------------------------------");
    	System.out.println(getClass().getSimpleName() + " destroyed");
    	System.out.println("-------------------------------------");
    }

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
        final AsyncContext aCtx = request.startAsync(request, response);
        AsynchExecutor.addJob(() -> {
        	//ServletRequest servletRequest = aCtx.getRequest();
            try {
				aCtx.getResponse()
				.getWriter()
				.print(getClass().getSimpleName() +  " call performed at " + new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
        	aCtx.complete();
		});
    }
}