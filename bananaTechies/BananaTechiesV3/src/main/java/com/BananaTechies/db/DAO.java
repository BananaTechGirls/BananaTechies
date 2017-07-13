package com.BananaTechies.db;

import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public abstract class DAO {
	private static Logger logger = Logger.getLogger("DAO");

	public DataSource datasource;
	public DataSource datasourceR;
	
	protected Properties properties = null;

	protected static String url;
	protected static String user;
	protected static String password;
	protected static String dbdriver;
	protected static String poolresource;
	protected static DataSource dataSource;

	public DAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env"); 
			this.datasource = (DataSource)envContext.lookup("jdbc/bananaApp");
		}catch (Exception e) {
			logger.info("Error al instanciar Datasource!!!!");
			e.printStackTrace();
		}
		
		try {
			datasourceR = this.getPoolResource ("jdbc/bananaApp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Properties getProperties() {
		return this.properties;
	}

	public boolean loadProperties() {

		DAO.url 	 = properties.getProperty("url");
		DAO.user 	 = properties.getProperty("user");
		DAO.password = properties.getProperty("password");
		DAO.dbdriver = properties.getProperty("dbdriver");
		DAO.poolresource = properties.getProperty("pooldataSource");
		
		return true;
	}
		private DataSource getPoolResource(String poolresource) throws NamingException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			this.datasource = (DataSource)  envContext.lookup(poolresource);
		} catch (Exception e) {
			logger.info("Error al instanciar Datasource!!!!");
			e.printStackTrace();
		}
		return dataSource;
	

	}
}
