package com.posidex.config;

import java.util.Properties;
import javax.jms.ConnectionFactory;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;


public class OracleAqConnectionFactoryInitialiserDs{	
	public ConnectionFactory createConnectionFactory(String URL,
	String userName,	String password,	String useDS,	String maxLimit,	String minLimit,
	String initialLimit,	String inactivityTimeOut, int maxActiveSession) throws Exception{
		/*Properties props=new Properties();
    	props.put("user", userName);
    	props.put("password", password);
    	if(useDS.equalsIgnoreCase("true")){*/
		
		String cacheName="ImplicitCache01"+System.nanoTime();
		System.out.println("Username "+userName+" Passsword:: "+password+" URL:: "+URL+ "minLimit :" +minLimit + "maxLimit:: "+maxLimit +"inactivityTimeOut::  "+inactivityTimeOut+" Cache Name :::"+cacheName);
    		OracleDataSource basicDataSource = new OracleDataSource();
        	Properties props1=new Properties();
        	props1.put("MinLimit",Integer.parseInt(minLimit));
        	props1.put("MaxLimit",Integer.parseInt(maxLimit));
        	props1.put("InitialLimit",Integer.parseInt(initialLimit));
        	props1.put("InactivityTimeout",Integer.parseInt(inactivityTimeOut)); 
        	props1.put("MaxActiveSessionPerConnection",maxActiveSession);
        	basicDataSource.setURL(URL);
    		basicDataSource.setUser(userName);
    		basicDataSource.setPassword(password);
    		basicDataSource.setConnectionCachingEnabled(true);
    		basicDataSource.setConnectionCacheProperties(props1);
    		basicDataSource.setConnectionCacheName(cacheName);
    		return oracle.jms.AQjmsFactory.getQueueConnectionFactory(basicDataSource);
    	/*}
    	else{
    		return oracle.jms.AQjmsFactory.getQueueConnectionFactory(URL, props);
    	}*/
  }  
}
