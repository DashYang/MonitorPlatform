package dash.client;

import java.util.ArrayList;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import dash.server.Info;


public class ClientEngine {
	private static Logger logger = Logger.getLogger(ClientEngine.class);
	
	public static void main(String args[]){
		  JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
          svr.setServiceClass(Info.class);
          svr.setAddress("http://localhost:8888/info");
          Info hw = (Info) svr.create();
 //       logger.info(hw.getAllKeys());
 //       logger.info(hw.getInfo("getserverinfo"));
          ArrayList<String> name = new ArrayList<String>();
          name.add("sh");
          name.add("mount");
          name.add("repvol");
          name.add("/gluster/replicate");
          logger.info(hw.executeCommand(name));
	}
}
