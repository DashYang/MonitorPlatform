package monitor.action;

import java.util.ArrayList;
import java.util.Map;

import monitor.validator.LevelTwoValidator;
import monitor.validator.ValidatorInterface;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import database.provider.GmsiteManage;
import database.provider.SQLinterface;
import hibernate.pojo.GmSite;

public class SitemanageAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -94060193170327977L;
	private String id;
	private String url;
	private String port;
	private String description;
	private ArrayList<GmSite> sites = new ArrayList<GmSite>();
	static Logger logger = Logger.getLogger(SitemanageAction.class);
	
	
	public ArrayList<GmSite> getSites() {
		return sites;
	}
	public void setSites(ArrayList<GmSite> sites) {
		this.sites = sites;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String init() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String username = session.get("USER").toString();
		ValidatorInterface validator = new LevelTwoValidator();
		if(validator.run(username) == false)
			return "fail";
		SQLinterface sqLinterface = new GmsiteManage();
		ArrayList<Object> osites = sqLinterface.getList();
		for(Object osite:osites){
			if(osite instanceof GmSite){
				GmSite gSite = (GmSite) osite;
				logger.info("site:" +gSite.getUrl());
				sites.add(gSite);
			}
		}
		return "success";
	}
	
	public String addSite(){
		int port_num = Integer.parseInt(port);
		GmSite site = new GmSite(url, port_num, description, "true");
		SQLinterface sqLinterface = new GmsiteManage();
		logger.info("add " + site);
		sqLinterface.add(site);
		ArrayList<Object> osites = sqLinterface.getList();
		for(Object osite:osites){
			if(osite instanceof GmSite){
				GmSite gSite = (GmSite) osite;
				logger.info("site:" +gSite.getUrl());
				sites.add(gSite);
			}
		}
		return "success";
	}
	
	public String deleteSite(){
		SQLinterface sqLinterface = new GmsiteManage();
		int id_num = Integer.parseInt(id);
		sqLinterface.deleteById(id_num);
		logger.info("delete site id = " + id );
		return "success";
	}
}
