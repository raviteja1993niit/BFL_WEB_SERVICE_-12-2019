/*package com.posidex.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.posidex.exception.PosidexException;

@Component
public class CROSFilter implements Filter {

	@Autowired
	private Environment env;

	private final Logger logger = LoggerFactory.getLogger(CROSFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		String requestIP = getClientIp(request);
		// String requestIP = request.getRemoteAddr();
		logger.info("current ip is " + requestIP);
		List<String> authorizedIp = Arrays.asList(new String[] {
				env.getProperty("AllowedOrigin") });

		for (String ip : authorizedIp) {
			logger.debug("authorized ip is" + ip);
		}
		if (authorizedIp.contains(requestIP)) {
			logger.info("Authorized");
		} else {
			logger.info("UnAuthorized");
				// throw new PosidexException("Unauthorized User");
			//	throw new BadCredentialsException("Invalid IP Address");
			
		}
		// log.info("Filtering on........................................................... "+env.getProperty("AllowedOrigin"));
		HttpServletResponse response = (HttpServletResponse) res;
		// response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Origin",
				env.getProperty("AllowedOrigin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");

		chain.doFilter(request, res);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("CROSFilter init " + env.getProperty("AllowedOrigin"));
		
	}

	private static String getClientIp(ServletRequest request) {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = ((HttpServletRequest) request)
					.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}
}
*/