package database.provider;

import hibernate.pojo.GmSite;
import hibernate.pojo.GmUser;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class GmuserManager implements SQLinterface {
	
	private static Logger logger = Logger.getLogger(GmuserManager.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object> getList() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql="from hibernate.pojo.GmUser";
		Query query = session.createQuery(hsql);
		session.getTransaction().commit();
		ArrayList<Object> list = new ArrayList<Object>();
		list = (ArrayList<Object>) query.list();
		
		session.close();
		return list;
	}

	@Override
	public void add(Object o) {
		if(o instanceof GmUser){
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(o);
			logger.info(o);
			session.getTransaction().commit();
			
			session.close();
			}
	}

	@Override
	public void deleteById(int id) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		GmUser user = (GmUser) session.get(GmUser.class, id);
		session.delete(user);
		logger.info("delete" + user);
		session.getTransaction().commit();
		
		session.close();
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}
	
	/****
	 * validate for loginaction
	 * @param u
	 * @return
	 */
	public GmUser getUser(GmUser u) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql="from hibernate.pojo.GmUser as user where user.name=:name and user.password=:password";
		Query query = session.createQuery(hsql);
		query.setString("name", u.getName());
		query.setString("password", u.getPassword());
		session.getTransaction().commit();
		@SuppressWarnings("unchecked")
		ArrayList<GmUser> list = (ArrayList<GmUser>)query.list();
		GmUser user = null;
		if(list.size()>0){
			user = list.get(0);
		}
		
		session.close();
		return user;
	}
	
	/**
	 * validate User's info such as authority
	 * @param user'sname
	 * @return
	 */
	public GmUser getUserByName(String name) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql="from hibernate.pojo.GmUser as user where user.name=:name";
		Query query = session.createQuery(hsql);
		query.setString("name", name);
		session.getTransaction().commit();
		@SuppressWarnings("unchecked")
		ArrayList<GmUser> list = (ArrayList<GmUser>)query.list();
		GmUser user = null;
		if(list.size()>0){
			user = list.get(0);
		}
		
		session.close();
		return user;
	}

}
