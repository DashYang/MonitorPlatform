package dash.server;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;

import dash.tools.PropertiesReader;

public class ServerEngine {
	private static Logger logger = Logger.getLogger(ServerEngine.class);
	
	public static void main(String args[]){
		InfoImpl implementor = new InfoImpl();
		String ip = PropertiesReader.getValueFromConfig("ip");
		String port = PropertiesReader.getValueFromConfig("port");
		String servername = PropertiesReader.getValueFromConfig("servername");
		String address = "http://" + ip + ":" + port + "/" + servername;
		Endpoint.publish(address, implementor);
		logger.info("web service started");
	}
}
