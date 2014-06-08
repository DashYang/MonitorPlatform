package monitor.action;

import java.sql.Date;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import database.provider.GmsiteManage;
import database.provider.GmstatusManage;

public class LogsmanageAction extends ActionSupport{
	private int id;
	private String hostname;
	private ArrayList<Date> logdate = new ArrayList<Date>();
	Logger logger = Logger.getLogger(LogsmanageAction.class);
	
	
	public ArrayList<Date> getLogdate() {
		return logdate;
	}
	public void setLogdate(ArrayList<Date> logdate) {
		this.logdate = logdate;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String init() {
		GmsiteManage gm = new GmsiteManage();
		hostname = gm.getSiteById(id).getDescription();
		GmstatusManage gsm = new GmstatusManage();
		logdate = gsm.getLogsdate(hostname);
		return "success";
	}
}
