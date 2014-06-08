package monitor.action;


import hibernate.pojo.GmUser;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import monitor.utilities.ConfigReader;
import monitor.utilities.MD5Util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.PropertiesReader;

import database.provider.GmuserManager;
import database.provider.SQLinterface;

public class Indexform extends ActionSupport {

	private String username;
	private String password;
	static Logger logger = Logger.getLogger(Indexform.class);
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	
	public String login() {
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
		logger.info("login");
		logger.info("begin to validate user" + " " + username + " " + password);
		try {
			String orginusername = ConfigReader.getValue("username");
			String orginpassword = ConfigReader.getValue("password");
			int orginlevel = Integer.parseInt(ConfigReader.getValue("level"));
			GmUser orginUser = new GmUser(orginusername, orginpassword, orginlevel);
			if(username.equals(orginusername) && password.equals(orginpassword)){
				session.put("USER", username);
				logger.info("orgin user");
				return "success";
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		String hashedpassword = MD5Util.MD5(password);
		GmUser user = new GmUser(username, hashedpassword, 0);
		GmuserManager manager  = new GmuserManager();
		GmUser result = manager.getUser(user);
		if(result != null){
		    session.put("USER", result.getName());
			logger.info(result.getName() + " log in! level is " + result.getLevel() );
			return "success";
		}
	    return "failed";
	}
	
	public String logout() {
		ActionContext actionContext = ActionContext.getContext();
	    Map session = actionContext.getSession();
	    String username = (String) session.get("USER");
	    logger.info("user " + username + "log out");
	    session.put("USER", "");
	    return "success";
	}
	
	public String execute() {
		System.out.println("execute");
		System.out.println(username + "  " + password);
	    return "failed";
	}

}
