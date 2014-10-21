package ufo.camel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CamelConfig {

    @Bean
    public ConnectionFactory getConnectionFactory() {
    	//creates an embedded ActiveMQ broker
    	//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
    	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    	connectionFactory.setBrokerURL("vm://localhost.spring.javaconfig?marshal=false&broker.persistent=false&broker.useJmx=false");
    	return connectionFactory;
    }

}