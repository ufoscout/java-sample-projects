package org.jboss.resteasy.plugins.server.servlet;
/*
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.scannotation.WarUrlFinder;

import javax.servlet.ServletContext;
import java.net.URL;

 // E' una modifica del listener che gli permette di funzionare col
 // jetty-plugin di maven
public class ListenerBootstrap extends ConfigurationBootstrap
{
   protected ServletContext servletContext;

   public ListenerBootstrap(ServletContext servletContext)
   {
      this.servletContext = servletContext;
   }

   @Override
   public ResteasyDeployment createDeployment()
   {
      ResteasyDeployment deployment = (ResteasyDeployment) servletContext.getAttribute(ResteasyDeployment.class.getName());
      if (deployment == null) deployment = super.createDeployment();
      deployment.getDefaultContextObjects().put(ServletContext.class, servletContext);
      return deployment;
   }

   public URL[] getScanningUrls()
   {
      URL[] urls = WarUrlFinder.findWebInfLibClasspaths(servletContext);
      URL url = WarUrlFinder.findWebInfClassesPath(servletContext);
      
      // Matthew Gilliard fix RESTeasy-251
      if (url == null){
    	  url = this.getClass().getClassLoader().getResource(".");
      }
      // End: RESTeasy-251
      
      if (url == null) return urls;
      URL[] all = new URL[urls.length + 1];
      int i = 0;
      for (i = 0; i < urls.length; i++)
      {
         all[i] = urls[i];
      }
      all[i] = url;
      return all;
   }

   public String getParameter(String name)
   {
      return servletContext.getInitParameter(name);
   }

   @Override
   public String getInitParameter(String name)
   {
      return servletContext.getInitParameter(name);
   }
}
*/
