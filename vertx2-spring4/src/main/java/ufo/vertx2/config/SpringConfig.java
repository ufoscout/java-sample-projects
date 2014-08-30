/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.vertx2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import ufo.vertx2.Lucene4BasePackagePlaceholder;

/**
 *
 * @author ufo
 */
@Configuration
@ComponentScan(basePackageClasses = Lucene4BasePackagePlaceholder.class)
@PropertySource("file:${" + Constants.FILESYSTEM_RESOURCES_PATH_VARIABLE + "}" + Constants.SPRING_PROPERTIES_FILE_PATH)
public class SpringConfig {
    
     //This is needed to enable the @Value annotation to resolve properly their values
     @Bean
     public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
     }
    
}
