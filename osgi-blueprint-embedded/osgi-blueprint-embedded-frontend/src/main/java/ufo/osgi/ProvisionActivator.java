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

import org.osgi.framework.BundleContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.wiring.BundleRevision;

import javax.servlet.ServletContext;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class ProvisionActivator {


	private List<URL> findBundles(final ServletContext servletContext) throws Exception {
		ArrayList<URL> list = new ArrayList<URL>();
		for (Object o : servletContext.getResourcePaths("/WEB-INF/bundles/")) {
			String name = (String) o;
			if (name.endsWith(".jar")) {
				URL url = servletContext.getResource(name);
				if (url != null) {
					list.add(url);
				}
			}
		}

		return list;
	}

	public void start(final BundleContext context, final ServletContext servletContext) throws Exception {
		servletContext.setAttribute(BundleContext.class.getName(), context);

		ArrayList<Bundle> installed = new ArrayList<Bundle>();
		for (URL url : findBundles(servletContext)) {
			System.out.println("-------------------------------------------");
			System.out.println("Installing bundle [" + url + "]");
			System.out.println("-------------------------------------------");
			Bundle bundle = context.installBundle(url.toExternalForm());
			installed.add(bundle);
		}

		File dir = new File("./target/bundles");
		if (dir.isDirectory()) {
			for (File file : new File("./target/bundles").listFiles()) {
				System.out.println("-------------------------------------------");
				System.out.println("Installing bundle [" + file.getAbsolutePath() + "]");
				System.out.println("-------------------------------------------");
				Bundle bundle = context.installBundle("reference:" + file.toURI().toString());
				installed.add(bundle);
			}
		}

		for (Bundle bundle : installed) {
			if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null) {
				System.out.println("-------------------------------------------");
				System.out.println("Starting bundle [" + bundle.getSymbolicName() + "]");
				System.out.println("-------------------------------------------");
				bundle.start();
			}
		}
	}

}
