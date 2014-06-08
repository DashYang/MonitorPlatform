package database.provider;

import java.util.ArrayList;

public interface SQLinterface {
	/***
	 * get result from dbs
	 * @return ArrayList
	 */
	public ArrayList<Object> getList();
	
	/****
	 * add an obeject to dbs
	 * @param o for object
	 */
	public void add(Object o);
	
	/**
	 * delete an object via it's id
	 * @param id
	 */
	public void deleteById(int id);
	
	/**
	 * update an object
	 * @param o for object
	 */
	public void update(Object o);
}
