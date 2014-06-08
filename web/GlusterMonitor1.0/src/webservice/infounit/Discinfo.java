package webservice.infounit;

public class Discinfo {
	private String hostname;
	private String disk;
	private String disksize;
	private String uuid;
	private String fstype;
	private String used;

	public Discinfo(String str){
		String[] tmp = str.split(" ");
		if(tmp.length >= 6){
			hostname = tmp[0];
			disk = tmp[1];
			disksize = tmp[2];
			uuid = tmp[3];
			fstype = tmp[4];
			used = tmp[5];
		}
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getDisksize() {
		return disksize;
	}

	public void setDisksize(String disksize) {
		this.disksize = disksize;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFstype() {
		return fstype;
	}

	public void setFstype(String fstype) {
		this.fstype = fstype;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

}
