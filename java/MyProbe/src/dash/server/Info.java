package dash.server;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface Info {
	@WebMethod
	@WebResult public ArrayList<String> getInfo(@WebParam(name="name")String name);
	
	@WebMethod
	@WebResult public ArrayList<String> getAllKeys();
	
	@WebMethod
	@WebResult public ArrayList<String> executeCommand(@WebParam(name="name")ArrayList<String> name);
	
}
