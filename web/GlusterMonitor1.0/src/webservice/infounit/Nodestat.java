package webservice.infounit;

public class Nodestat {
	private String hostname;
	private String nodestat;
	private String diskvolume;
	private String manageip;
	private String trafficip;
	private String ibip;
	private String nfsstatus;
	private String smbstatus;
	private String ctdbstatus;
	private String iscsistatu;
	private String unistoragestatus;
	
	public Nodestat(String str) {
		// TODO Auto-generated constructor stub
		String[] tmp = str.split(",");
		if(tmp.length >= 11){
			hostname = tmp[0];
			nodestat = tmp[1];
			diskvolume = tmp[2];
			manageip = tmp[3];
			trafficip = tmp[4];
			ibip = tmp[5];
			nfsstatus = tmp[6];
			smbstatus = tmp[7];
			ctdbstatus = tmp[8];
			iscsistatu = tmp[9];
			unistoragestatus = tmp[10];
		}
	}
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getNodestat() {
		return nodestat;
	}
	public void setNodestat(String nodestat) {
		this.nodestat = nodestat;
	}
	public String getDiskvolume() {
		return diskvolume;
	}
	public void setDiskvolume(String diskvolume) {
		this.diskvolume = diskvolume;
	}
	public String getManageip() {
		return manageip;
	}
	public void setManageip(String manageip) {
		this.manageip = manageip;
	}
	public String getTrafficip() {
		return trafficip;
	}
	public void setTrafficip(String trafficip) {
		this.trafficip = trafficip;
	}
	public String getIbip() {
		return ibip;
	}
	public void setIbip(String ibip) {
		this.ibip = ibip;
	}
	public String getNfsstatus() {
		return nfsstatus;
	}
	public void setNfsstatus(String nfsstatus) {
		this.nfsstatus = nfsstatus;
	}
	public String getSmbstatus() {
		return smbstatus;
	}
	public void setSmbstatus(String smbstatus) {
		this.smbstatus = smbstatus;
	}
	public String getCtdbstatus() {
		return ctdbstatus;
	}
	public void setCtdbstatus(String ctdbstatus) {
		this.ctdbstatus = ctdbstatus;
	}
	public String getIscsistatu() {
		return iscsistatu;
	}
	public void setIscsistatu(String iscsistatu) {
		this.iscsistatu = iscsistatu;
	}
	public String getUnistoragestatus() {
		return unistoragestatus;
	}
	public void setUnistoragestatus(String unistoragestatus) {
		this.unistoragestatus = unistoragestatus;
	}
	
	
	
}
