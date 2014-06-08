package storage;

public class Statusunit {
	private String date;
	private String hostname;
	private String ipofib;
	private String cpuuseratio;
	private String memtotal;
	private String usedmem;
	private String netsendflow;
	private String netreceiveflow;
	private String ibsendflow;
	private String ibreceiveflow;
	
	public Statusunit(String str){
		String[] tmp = str.split(",");
		if(tmp.length >= 9){
			date = tmp[0].split("@")[0];
			hostname = tmp[0].split("@")[1];
			ipofib = tmp[1];
			cpuuseratio = tmp[2];
			memtotal = tmp[3];
			usedmem = tmp[4];
			netsendflow = tmp[5];
			netreceiveflow = tmp[6];
			ibsendflow = tmp[7];
			ibreceiveflow = tmp[8];
			
		}
	}
	
	public String getIpofib() {
		return ipofib;
	}
	
	public Statusunit(String date, String hostname, String ipofib,
			String cpuuseratio, String memtotal, String usedmem,
			String netsendflow, String netreceiveflow, String ibsendflow,
			String ibreceiveflow) {
		super();
		this.date = date;
		this.hostname = hostname;
		this.ipofib = ipofib;
		this.cpuuseratio = cpuuseratio;
		this.memtotal = memtotal;
		this.usedmem = usedmem;
		this.netsendflow = netsendflow;
		this.netreceiveflow = netreceiveflow;
		this.ibsendflow = ibsendflow;
		this.ibreceiveflow = ibreceiveflow;
	}

	public void setIpofib(String ipofib) {
		this.ipofib = ipofib;
	}


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getCpuuseratio() {
		return cpuuseratio;
	}
	public void setCpuuseratio(String cpuuseratio) {
		this.cpuuseratio = cpuuseratio;
	}
	public String getMemtotal() {
		return memtotal;
	}
	public void setMemtotal(String memtotal) {
		this.memtotal = memtotal;
	}
	public String getUsedmem() {
		return usedmem;
	}
	public void setUsedmem(String usedmem) {
		this.usedmem = usedmem;
	}
	public String getNetsendflow() {
		return netsendflow;
	}
	public void setNetsendflow(String netsendflow) {
		this.netsendflow = netsendflow;
	}
	public String getNetreceiveflow() {
		return netreceiveflow;
	}
	public void setNetreceiveflow(String netreceiveflow) {
		this.netreceiveflow = netreceiveflow;
	}
	public String getIbsendflow() {
		return ibsendflow;
	}
	public void setIbsendflow(String ibsendflow) {
		this.ibsendflow = ibsendflow;
	}
	public String getIbreceiveflow() {
		return ibreceiveflow;
	}
	public void setIbreceiveflow(String ibreceiveflow) {
		this.ibreceiveflow = ibreceiveflow;
	}
	
	
}
