/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ufo.jetty9.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author ufo
 */
@Configuration
@EnableWebMvc
public class SpringDispatcherConfig extends WebMvcConfigurerAdapter {

	public SpringDispatcherConfig() {
		System.out.println("SpringDispatcherConfig Started");
	}
}
