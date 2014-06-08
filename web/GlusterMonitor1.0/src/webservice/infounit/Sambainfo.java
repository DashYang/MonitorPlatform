package webservice.infounit;

public class Sambainfo {
	private String dirname;
	private String path;
	private String guest;
	private String readonly;
	private String group;
	private String user;
	
	public Sambainfo(String str){
		String[] tmp = str.split(";");
		if(tmp.length >= 6){
			dirname = tmp[0];
			path = tmp[1];
			guest = tmp[2];
			readonly = tmp[3];
			group = tmp[4];
			user = tmp[5];
		}
	}
	public String getDirname() {
		return dirname;
	}
	public void setDirname(String dirname) {
		this.dirname = dirname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
