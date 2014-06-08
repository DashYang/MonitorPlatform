package webservice.infounit;

import java.util.ArrayList;

public class Serverinfo {
	private String os = "";
	private String cpu = "";
	private String kernelname = "";
	private String kernelrelease = "";
	
	public Serverinfo(ArrayList<String> info) {
		if(info.size() >= 4){
			os = info.get(0);
			cpu = info.get(1);
			kernelname = info.get(2);
			kernelrelease = info.get(3);
		}
	}
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getKernelname() {
		return kernelname;
	}
	public void setKernelname(String kernelname) {
		this.kernelname = kernelname;
	}
	public String getKernelrelease() {
		return kernelrelease;
	}
	public void setKernelrelease(String kernelrelease) {
		this.kernelrelease = kernelrelease;
	}	
}
