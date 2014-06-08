package webservice.infounit;

public class Peerinfo {
	private String peer;
	private String connectstatus;
	private String ipaddress;
	
	public Peerinfo(String str){
		String[] tmp = str.split(" ");
		if(tmp.length >= 3){
			peer = tmp[0];
			connectstatus = tmp[1];
			ipaddress = tmp[2];
		}
	}
	public String getPeer() {
		return peer;
	}
	public void setPeer(String peer) {
		this.peer = peer;
	}
	public String getConnectstatus() {
		return connectstatus;
	}
	public void setConnectstatus(String connectstatus) {
		this.connectstatus = connectstatus;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	
}
