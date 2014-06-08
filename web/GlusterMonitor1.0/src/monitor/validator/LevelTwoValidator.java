package monitor.validator;

import hibernate.pojo.GmUser;
import monitor.utilities.ConfigReader;
import database.provider.GmuserManager;

public class LevelTwoValidator implements ValidatorInterface {

	@Override
	public boolean run(Object o) {
		if(o instanceof String){
			String orginusername = ConfigReader.getValue("username");
			
			if(orginusername.equals(o)){
				int level = Integer.parseInt(ConfigReader.getValue("level"));
				if(level >= 2)
					return true;
			}
			
			GmUser user = null;
			user = new GmuserManager().getUserByName((String) o);
			if(user != null && user.getLevel() >=2 )
				return true;
		}
		return false;
	}

}
