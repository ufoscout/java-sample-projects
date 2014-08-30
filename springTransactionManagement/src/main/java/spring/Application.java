package spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	BeanFactory ctx;
	
	public Application() {
		ctx = new ClassPathXmlApplicationContext("spring-context.xml");
		
//		PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
//		cfg.setLocation(new FileSystemResource("config/config.properties"));
//		cfg.postProcessBeanFactory(ctx);
	}
	
	public DataSource getDatasource() {
		return (DataSource) ctx.getBean("dataSource", DataSource.class);
	}
	
	public <T> T getBean(String name, Class<T> className) {
		return (T) ctx.getBean(name, className);
	}
	
}
