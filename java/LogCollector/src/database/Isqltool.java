package database;

import java.util.*;

public interface Isqltool {
	public boolean add(Object item);
	
	/*************
	 * 
	 * @param date
	 * @param name
	 * @param flag   value is true means get latest unit
	 * @return
	 */
	public ArrayList<Object> show(String date,String name,boolean flag);
	
}
