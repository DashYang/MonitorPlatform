package dash.storage.unit;

public class VolumeInfo {
	private String type;
	private String status;
	private String numberofbrick;
	private String transporttype;
	private String bricks;
	
	
	public VolumeInfo() {
		super();
	}
	public VolumeInfo(String type, String status, String numberofbrick,
			String transporttype, String bricks) {
		super();
		this.type = type;
		this.status = status;
		this.numberofbrick = numberofbrick;
		this.transporttype = transporttype;
		this.bricks = bricks;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNumberofbrick() {
		return numberofbrick;
	}
	public void setNumberofbrick(String numberofbrick) {
		this.numberofbrick = numberofbrick;
	}
	public String getTransporttype() {
		return transporttype;
	}
	public void setTransporttype(String transporttype) {
		this.transporttype = transporttype;
	}
	public String getBricks() {
		return bricks;
	}
	public void setBricks(String bricks) {
		this.bricks = bricks;
	}
	
	
}
