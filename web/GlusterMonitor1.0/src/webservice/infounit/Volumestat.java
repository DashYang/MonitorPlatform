package webservice.infounit;


public class Volumestat {
	private String volume;
	private String used;
	private String available;
	private String mounton;
	
	public Volumestat(String str){
		String[] tmp = str.split(" ");
		if(tmp.length >= 4){
			volume = tmp[0];
			used = tmp[1];
			available = tmp[2];
			mounton = tmp[3];
		}
	}
	
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getMounton() {
		return mounton;
	}
	public void setMounton(String mounton) {
		this.mounton = mounton;
	}
	
	
}
