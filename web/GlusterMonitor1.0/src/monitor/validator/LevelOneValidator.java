package monitor.validator;

import database.provider.GmuserManager;
import monitor.utilities.ConfigReader;
import hibernate.pojo.GmUser;

public class LevelOneValidator implements ValidatorInterface {

	@Override
	public boolean run(Object o) {
		if(o instanceof String){
			String orginusername = ConfigReader.getValue("username");
			if(orginusername.equals(o)){
				int level = Integer.parseInt(ConfigReader.getValue("level"));
				if(level >= 1)
					return true;
			}
			GmUser user = null;
			user = new GmuserManager().getUserByName((String) o);
			if(user != null && user.getLevel() >=1 )
				return true;
		}
		return false;
	}

}
