package com.sm.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext context) throws ServletException {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("/WEB-INF/application-context.xml");

		ContextLoaderListener contextLoaderListener = new ContextLoaderListener(
				appContext);
		context.addListener(contextLoaderListener);

		XmlWebApplicationContext webContext = new XmlWebApplicationContext();
		webContext.setConfigLocation("/WEB-INF/dispatcher-servlet.xml");
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
		ServletRegistration.Dynamic dynamic = context.addServlet("dispatcher",
				dispatcherServlet);
		dynamic.setLoadOnStartup(2);
		dynamic.addMapping("*.htm");
	}
}
