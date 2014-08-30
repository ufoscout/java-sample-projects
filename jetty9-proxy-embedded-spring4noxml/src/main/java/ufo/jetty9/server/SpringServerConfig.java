/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.jetty9.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ufo
 */
@Configuration
@ComponentScan(basePackageClasses = BasePackagePlaceholder.class)
public class SpringServerConfig {

	public SpringServerConfig() {
		System.out.println("Context Configuration Started");
	}
}
