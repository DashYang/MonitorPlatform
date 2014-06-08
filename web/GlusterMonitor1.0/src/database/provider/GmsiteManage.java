package database.provider;

import hibernate.pojo.GmSite;
import hibernate.pojo.GmUser;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;


public class GmsiteManage implements SQLinterface {
	static Logger logger = Logger.getLogger(GmuserManager.class);
	@Override
	public ArrayList<Object> getList() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql="from hibernate.pojo.GmSite as site where site.display='true'";
		Query query = session.createQuery(hsql);
		session.getTransaction().commit();
		ArrayList<Object> list = new ArrayList<Object>();
		list = (ArrayList<Object>) query.list();
		
		session.close();
		return list;
	}

	@Override
	public void add(Object o) {
		if(o instanceof GmSite){
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
		GmSite site = (GmSite) session.get(GmSite.class, id);
		site.setDisplay("false");
		session.update(site);
		logger.info("delete" + site);
		session.getTransaction().commit();
		
		session.close();
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}
	
	public GmSite getSiteById(int id) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql="from hibernate.pojo.GmSite as site where site.display='true' and site.id=:id";
		Query query = session.createQuery(hsql);
		query.setInteger("id", id);
		session.getTransaction().commit();
		ArrayList<Object> list = new ArrayList<Object>();
		list = (ArrayList<Object>) query.list();
		GmSite resSite = (GmSite) list.get(0);
		
		session.close();
		return resSite;
	}
}
