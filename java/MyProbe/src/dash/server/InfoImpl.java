package dash.server;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(endpointInterface = "dash.server.Info", serviceName = "Info")
public class InfoImpl implements Info {

	@Override
	@WebMethod
	@WebResult
	public ArrayList<String> getInfo(@WebParam(name = "name") String name) {
		return Collector.getInfoByKey(name);
	}

	@Override
	@WebMethod
	@WebResult
	public ArrayList<String> getAllKeys() {
		return Collector.collectKeys();
	}

	@Override
	@WebMethod
	@WebResult
	public ArrayList<String> executeCommand(
			@WebParam(name = "name") ArrayList<String> name) {
		return Collector.executeCommand(name);
	}
	
	
}
