package webservice.interfaces;

import java.util.ArrayList;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientEngine {
	Info info = null;
	public ClientEngine(String address){
		JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
        svr.setServiceClass(Info.class);
        svr.setAddress(address + "/info");
        info = (Info) svr.create();
	}
	
	public ArrayList<String> getAllKeys(){
		return info.getAllKeys();
	}
	
	public ArrayList<String> getInfo(String name){
		return info.getInfo(name);
	}
	
	public ArrayList<String> executeCommand(ArrayList<String> cmds) {
		return info.executeCommand(cmds);
	}
	
	public static void main(String args[]){
		  JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
          svr.setServiceClass(Info.class);
          svr.setAddress("http://192.168.230.22:8888/info");
          Info hw = (Info) svr.create();
	}
}
