package database.provider;

import java.sql.Connection;
import java.sql.DriverManager;

import monitor.utilities.ConfigReader;


/***
 * to get SQl connenction
 * @author dash
 *
 */
public class SQLconnect {

	private static String driver ;
	private static String url ;
	private static String user;
	private static String pwd;
	
	public static Connection getConnection() {
			driver = ConfigReader.getValue("driver");
			url = ConfigReader.getValue("url");
			user = ConfigReader.getValue("user");
			pwd = ConfigReader.getValue("pwd");
		try {
			// load driver
			Class.forName(driver);
			// link to database
			Connection conn = DriverManager.getConnection(url, user, pwd);
			if (!conn.isClosed())
			{
		//		System.out.println("Succeeded connecting to the Database!");
				return conn;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}