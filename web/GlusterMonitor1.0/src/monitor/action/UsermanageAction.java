package monitor.action;

import hibernate.pojo.GmUser;

import java.util.ArrayList;
import java.util.Map;

import monitor.utilities.MD5Util;
import monitor.validator.LevelTwoValidator;
import monitor.validator.ValidatorInterface;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import database.provider.GmuserManager;
import database.provider.SQLinterface;

public class UsermanageAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String password;
	private String level;

	ArrayList<GmUser> users = new ArrayList<GmUser>();
	static Logger logger = Logger.getLogger(UsermanageAction.class);

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public ArrayList<GmUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<GmUser> users) {
		this.users = users;
	}
	
	/**
	 * 获得用户列表
	 */
	private void getUserList() {
		SQLinterface sqLinterface = new GmuserManager();
		ArrayList<Object> ousers = sqLinterface.getList();
		for (Object o : ousers) {
			if (o instanceof GmUser) {
				GmUser tmpGmUser = (GmUser) o;
				users.add(tmpGmUser);
			}
		}
	}
	
	public String init() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String username = session.get("USER").toString();
		ValidatorInterface validator = new LevelTwoValidator();
		if(validator.run(username) == false)
			return "fail";
		getUserList();
		return "success";
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String deleteUser() {
		SQLinterface sqLinterface = new GmuserManager();
		int id_num = Integer.parseInt(id);
		sqLinterface.deleteById(id_num);
		return "success";
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	public String addUser() {
		logger.info(username + " " + password + " " + level);
		GmuserManager manager = new GmuserManager();
		GmUser finduser = manager.getUserByName(username);
		if (finduser == null && !username.equals("admin")) {
			SQLinterface sqLinterface = new GmuserManager();
			int level_num = Integer.parseInt(level);
			String hashedpassword = MD5Util.MD5(password);
			GmUser user = new GmUser(username, hashedpassword, level_num);
			sqLinterface.add(user);
		}
		getUserList();
		return "success";
	}
}
