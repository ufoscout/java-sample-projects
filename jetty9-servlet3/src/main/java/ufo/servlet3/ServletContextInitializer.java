package ufo.servlet3;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-------------------------------------");
		System.out.println(getClass().getSimpleName() + " started");
		System.out.println("-------------------------------------");

		//AsynchExecutor.start();

		ServletContext servletContext = sce.getServletContext();

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("AsynchProgrammaticServlet", new AsynchProgrammaticServlet());
		dispatcher.setLoadOnStartup(1);
		dispatcher.setAsyncSupported(true);
		dispatcher.addMapping("/asynchTwo");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("-------------------------------------");
		System.out.println(getClass().getSimpleName() + " destroyed");
		System.out.println("-------------------------------------");
	}

}
