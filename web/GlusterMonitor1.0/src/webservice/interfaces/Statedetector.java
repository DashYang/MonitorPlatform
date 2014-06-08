package webservice.interfaces;

import org.apache.log4j.Logger;


public class Statedetector {
	static Logger logger = Logger.getLogger(Statedetector.class);
	
	public static boolean isOnline(String address){
		try{
			ClientEngine cEngine = new ClientEngine(address);
			cEngine.getInfo("init");
		}catch(Exception e){
			logger.info(e.toString());
			return false;
		}
		return true;
	}
}
