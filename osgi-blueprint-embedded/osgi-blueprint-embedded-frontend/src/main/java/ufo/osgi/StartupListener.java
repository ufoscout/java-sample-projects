/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ufo.osgi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.apache.felix.framework.util.FelixConstants;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

public final class StartupListener implements ServletContextListener {

	private Framework framework;

	@Override
	public void contextDestroyed(final ServletContextEvent event) {
		try {
			framework.stop();
			System.out.println("Felix container stopped!");
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(final ServletContextEvent event) {
		try {
			ServletContext servletContext = event.getServletContext();
			FrameworkFactory frameworkFactory = ServiceLoader
					.load(FrameworkFactory.class).iterator().next();
			Map<String, String> config = createConfig(servletContext);
			// TODO: add some config properties
			framework = frameworkFactory.newFramework(config);

			framework.start();
			System.out.println("Felix container started!");

			new ProvisionActivator().start(framework.getBundleContext(), servletContext);
			System.out.println("Bundles started!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> createConfig(final ServletContext servletContext) {

		Map<String, String> map = new HashMap<String, String>();

		map.put(Constants.FRAMEWORK_STORAGE, "./target/osgi-cache");
		//		Properties props = new Properties();
		//		props.load(servletContext.getResourceAsStream("/WEB-INF/framework.properties"));
		//		for (Object key : props.keySet()) {
		//			map.put(key.toString(), props.get(key));
		//		}

		return map;

	}
}
