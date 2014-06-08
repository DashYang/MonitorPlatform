package monitor.action;

import java.util.ArrayList;

import hibernate.pojo.GmSite;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import webservice.infounit.Volumestat;
import webservice.interfaces.ClientEngine;
import database.provider.GmsiteManage;

public class CommandHandlerAction implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private static Logger logger = Logger.getLogger(CommandHandlerAction.class);
	private String result;
	
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

	}
	
	/*****
	 * get cxf engine by post id
	 * @return
	 */
	private ClientEngine getEngine() {
		String siteid = request.getParameter("siteid");
		int id = Integer.parseInt(siteid);
		
		logger.info("id  = " + id);
		GmSite site = new GmsiteManage().getSiteById(id);
		logger.info(site);
		String address = site.getUrl() + ":" + site.getPort();
		ClientEngine cEngine = new ClientEngine(address);
		
		return cEngine; 
	}
	
	/**
	 * mount volume using shell
	 * @return
	 */
	public String mountVolume() {
		ClientEngine cEngine = getEngine();
		String volumename = request.getParameter("volumename");
		
		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("sh");
		cmds.add("mount");
		cmds.add(volumename);
		logger.info("command = " + cmds);
		
		result = "";
		ArrayList<String> shellresult = cEngine.executeCommand(cmds);
		if( shellresult == null || shellresult.size() <= 0) {
			result = "挂载成功";
		} else {
			for(String line : shellresult) {
				result += line + "<br>";
			}
		}
		
		return "success";
	}
	
	/**
	 * mount volume using shell
	 * @return
	 */
	public String umountVolume() {
		ClientEngine cEngine = getEngine();
		String volumename = request.getParameter("volumename");
		
		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("sh");
		cmds.add("umount");
		cmds.add(volumename);
		logger.info("command = " + cmds);
		
		result = "";
		ArrayList<String> shellresult = cEngine.executeCommand(cmds);
		if( shellresult == null || shellresult.size() <= 0) {
			result = "取消挂载成功";
		} else {
			for(String line : shellresult) {
				result += line + "<br>";
			}
		}
		
		return "success";
	}
	
	public String changeVolumeStatus() {
		ClientEngine cEngine = getEngine();
		String volumename = request.getParameter("volumename");
		String type = request.getParameter("type");
		
		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("sh");
		cmds.add(type);
		if(!volumename.equals(""))
			cmds.add(volumename);
		logger.info("command = " + cmds);
		
		result = "";
		ArrayList<String> shellresult = cEngine.executeCommand(cmds);
		if( shellresult == null || shellresult.size() <= 0) {
			result = "操作成功";
		} else {
			for(String line : shellresult) {
				result += line + "<br>";
			}
		}
		
		return "success";
	}
	
	public String editNodeStatus() {
		ClientEngine cEngine = getEngine();
		String nodename = request.getParameter("nodename");
		String type = request.getParameter("type");
		
		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("sh");
		cmds.add(type);
		if(!nodename.equals(""))
			cmds.add(nodename);
		logger.info("command = " + cmds);
		
		result = "";
		ArrayList<String> shellresult = cEngine.executeCommand(cmds);
		if( shellresult == null || shellresult.size() <= 0) {
			result = "操作成功";
		} else {
			for(String line : shellresult) {
				result += line + "<br>";
			}
		}
		
		return "success";
	}
	
	public String getBriefOfSambaInfo() {
		ClientEngine cEngine = getEngine();
		String nodename = request.getParameter("nodename");
		String type = request.getParameter("type");
		
		ArrayList<String> requestset = new ArrayList<String>(); 
		if(type.equals("path")) {
			ArrayList<String> str = cEngine.getInfo("getvolstat");
			if (str != null) {
				for (String temp : str) {
					Volumestat vstat = new Volumestat(temp);
					requestset.add(vstat.getMounton());
				}
			}
		}else if(type.equals("user")) {
			ArrayList<String> str = cEngine.getInfo("getsambauser");
			if (str != null) {
				for (String temp : str) {
					requestset.add(temp);
				}
			}
		}
		
		for(String option : requestset) {
			result += "<option>" + option + "</option>";
		}
		
		return "success";
	}
	
	public String editSambaFolder() {
		ClientEngine cEngine = getEngine();
		String nodename = request.getParameter("nodename");
		String type = request.getParameter("type");
		ArrayList<String> cmds = new ArrayList<String>(); 
				
		if(type.equals("delete")) {
			String sambaname = request.getParameter("sambaname");
			
			cmds.add("sh");
			cmds.add("deletesambadir");
			cmds.add(sambaname);
		}else if (type.equals("create")) {
			String sambaname = request.getParameter("sambaname");
			String path = request.getParameter("path");
			String user = request.getParameter("user");
			
			cmds.add("sh");
			cmds.add("createsambadir");
			cmds.add(sambaname);
			cmds.add(path);
			cmds.add(user);
		}
		
		ArrayList<String> shellresult = cEngine.executeCommand(cmds);
		if( shellresult == null || shellresult.size() <= 0) {
			result = "操作成功";
		} else {
			for(String line : shellresult) {
				result += line + "<br>";
			}
		}
		
		return "success";
	}
	
	public String editSambaUser() {
		ClientEngine cEngine = getEngine();
		String username = request.getParameter("username");
		String type = request.getParameter("type");
		ArrayList<String> cmds = new ArrayList<String>(); 
		
		cmds.add("sh");
		cmds.add(type);
		cmds.add(username);
		if(type.equals("addsambauser")) {
			String password = request.getParameter("password");
			cmds.add(password);
		}
		
		result = "";
		ArrayList<String> shellresult = cEngine.executeCommand(cmds);
		if( shellresult == null || shellresult.size() <= 0) {
			result = "操作成功";
		} else {
			for(String line : shellresult) {
				result += line + "<br>";
			}
		}
		
		return "success";
	}

}
