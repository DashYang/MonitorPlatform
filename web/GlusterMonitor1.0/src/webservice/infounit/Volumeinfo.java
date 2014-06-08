package webservice.infounit;

public class Volumeinfo {
	private String volumename;
	private String volumetype;
	private String transporttype;
	private String volumestatus;
	private String bricknum;
	private String repfac;
	private String brickelement;
	
	public Volumeinfo(String str){
		String[] tmp = str.split(",");
		if(tmp.length >= 7){
			volumename = tmp[0];
			volumetype = tmp[1];
			transporttype = tmp[2];
			volumestatus = tmp[3];
			bricknum = tmp[4];
			repfac = tmp[5];
			brickelement = tmp[6];
		}
		
	}
	public String getVolumename() {
		return volumename;
	}
	public void setVolumename(String volumename) {
		this.volumename = volumename;
	}
	public String getVolumetype() {
		return volumetype;
	}
	public void setVolumetype(String volumetype) {
		this.volumetype = volumetype;
	}
	public String getTransporttype() {
		return transporttype;
	}
	public void setTransporttype(String transporttype) {
		this.transporttype = transporttype;
	}
	public String getVolumestatus() {
		return volumestatus;
	}
	public void setVolumestatus(String volumestatus) {
		this.volumestatus = volumestatus;
	}
	public String getBricknum() {
		return bricknum;
	}
	public void setBricknum(String bricknum) {
		this.bricknum = bricknum;
	}
	public String getRepfac() {
		return repfac;
	}
	public void setRepfac(String repfac) {
		this.repfac = repfac;
	}
	public String getBrickelement() {
		return brickelement;
	}
	public void setBrickelement(String brickelement) {
		this.brickelement = brickelement;
	}
	
	
}
