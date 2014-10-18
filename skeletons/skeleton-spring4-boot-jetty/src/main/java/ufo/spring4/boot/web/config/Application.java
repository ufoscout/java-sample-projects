package ufo.spring4.boot.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ufo.spring4.boot.BasePackagePlaceholder;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = BasePackagePlaceholder.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//To add a second servlet
//	@Bean
//	public ServletRegistrationBean servletRegistrationBean(){
//	    return new ServletRegistrationBean(new FooServlet(),"/someOtherUrl/*");
//	}
}
