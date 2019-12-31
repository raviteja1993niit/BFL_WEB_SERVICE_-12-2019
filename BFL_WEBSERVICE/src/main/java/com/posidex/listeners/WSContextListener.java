
package com.posidex.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.posidex.dao.PosidexRestDAOI;
import com.posidex.dao.impl.PosidexRestDAOImpl;

@Component
public class WSContextListener implements ServletContextListener// ,EnvironmentAware
{
	@Autowired
	private PosidexRestDAOI daoi;

	@Autowired
	private Environment environment;

	private static Logger logger=Logger.getLogger(PosidexRestDAOImpl.class.getName());

	public void contextDestroyed(ServletContextEvent event) {
		ServletContext application = event.getServletContext();
		application.removeAttribute("errorcodes");
	}

	public void contextInitialized(ServletContextEvent event) {
		if (logger.isDebugEnabled())
			logger.debug("In contextInitialized()..");

		ServletContext application = event.getServletContext();

		Map<String, String> errorCodesMap = null;
		Map<String,ArrayList<String>> psxNspProfiles=null;
		try {

			errorCodesMap = daoi.getWebServiceErrorCodes();
			psxNspProfiles=daoi.getActiveProfiles();
			logger.info("Error codes Count:" + errorCodesMap.size());
			logger.info("Profile Deatils ::: "+ psxNspProfiles);
			logger.info("psxNspProfiles codes Count:" + psxNspProfiles.size());
			if (errorCodesMap != null && errorCodesMap.isEmpty()) {
				throw new Exception("There Should be Atleast One Error code Info in PSX_WEBSERVICE_ERROR_CODES_T");
			}
			application.setAttribute("errorcodes", errorCodesMap);
			application.setAttribute("psxNspProfiles", psxNspProfiles);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
