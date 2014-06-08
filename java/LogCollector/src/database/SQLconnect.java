package database;

import java.sql.Connection;
import java.sql.DriverManager;


import utilities.PropertiesReader;
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
		try {
			driver = PropertiesReader.getValue("driver");
			url = PropertiesReader.getValue("url");
			user = PropertiesReader.getValue("user");
			pwd = PropertiesReader.getValue("pwd");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
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