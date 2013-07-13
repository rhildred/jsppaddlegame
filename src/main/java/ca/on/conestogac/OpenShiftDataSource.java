package ca.on.conestogac;

import java.sql.*;

public class OpenShiftDataSource {
	/**
	 * Returns an Connection object that is connected to a Mysql database. the
	 * database can be either on localhost in the case of the Maven tomcat
	 * plugin or on Openshift. If it is on openshift it is from the environment
	 * variables. If the connection is on localhost, the url passed as a string
	 * is used.
	 * 
	 * @param the
	 *            url to use on localhost
	 * @return a connected connection
	 * @see Connection
	 */
	public static Connection getConnection(String sURL)
			throws ClassNotFoundException, InstantiationException,
			SQLException, IllegalAccessException {
		if (System.getenv("OPENSHIFT_MYSQL_DB_HOST") != null) {
			sURL = String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
					System.getenv("OPENSHIFT_MYSQL_DB_HOST"),
					System.getenv("OPENSHIFT_GEAR_NAME"),
					System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"),
					System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
		}

		Connection connection = null;

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection(sURL);

		return connection;
	}
}