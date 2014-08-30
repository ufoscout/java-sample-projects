package ufo.jetty9.reverseproxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ProxyContextInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringProxyConfig.class);

		// Manage the lifecycle of the root application context
		context.addListener(new ContextLoaderListener(rootContext));

		//register the proxy servlet
		ServletRegistration.Dynamic dispatcher = context.addServlet("UfoProxyServlet", new UfoProxyServlet());
		dispatcher.setLoadOnStartup(1);
		dispatcher.setAsyncSupported(true);
		dispatcher.addMapping("/proxy/*");

		System.out.println("ProxyContextInitializer started");

	}

}