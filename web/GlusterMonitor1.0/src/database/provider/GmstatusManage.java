package database.provider;

import hibernate.pojo.GmStatus;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metamodel.relational.Size;

public class GmstatusManage implements SQLinterface {
	
	Logger logger = Logger.getLogger(GmsiteManage.class);
	
	@Override
	public ArrayList<Object> getList() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql = "from hibernate.pojo.GmStatus as status where site.display='true'";
		Query query = session.createQuery(hsql);
		session.getTransaction().commit();
		ArrayList<Object> list = new ArrayList<Object>();
		list = (ArrayList<Object>) query.list();
		
		session.close();
		return list;
	}

	public ArrayList<Object> getListByHostname(String hostname) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql = "from hibernate.pojo.GmStatus as status where status.id.hostname=:hostname order by status.id.id desc";
		Query query = session.createQuery(hsql);
		query.setString("hostname", hostname);
		session.getTransaction().commit();
		ArrayList<Object> list = new ArrayList<Object>();
		query.setFirstResult(0);//开始从第几条开始取数据
        query.setMaxResults(9);//取多少条数据
		list = (ArrayList<Object>) query.list();
		ArrayList<Object> reverseList = new ArrayList<Object>();
		for(int i = list.size()-1;i>=0;i--){
			reverseList.add(list.get(i));
		}
		logger.info("return list size " + reverseList.size());
		
		session.close();
		return reverseList;
	}
	
	/***
	 * get after date's host info
	 * @param hostname
	 * @param date
	 * @return list
	 */
	public ArrayList<Object> getListByHostnameandTime(String hostname,String date){
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql = "from hibernate.pojo.GmStatus as status where status.id.hostname=:hostname and status.id.id>:date order by status.id.id asc";
		Query query = session.createQuery(hsql);
		query.setString("hostname", hostname);
		query.setString("date", date);
		session.getTransaction().commit();
		ArrayList<Object> list = new ArrayList<Object>();
		list = (ArrayList<Object>) query.list();
		logger.info("return list size " + list.size());
		
		session.close();
		return list;
	}
	
	public ArrayList<Date> getLogsdate(String hostname){
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hsql = "select distinct DATE(id.id) from hibernate.pojo.GmStatus as status where status.id.hostname=:hostname";
		Query query = session.createQuery(hsql);
		query.setString("hostname", hostname);
		session.getTransaction().commit();
		ArrayList<Date> list = new ArrayList<Date>();
		list = (ArrayList<Date>) query.list();
		logger.info("log number:" + list.size());
		
		session.close();
		return list;
		
	}
	
	public ArrayList<Object> getLogsdetail(String hostname,String date){
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date dateform = (Date) df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String hsql = "from hibernate.pojo.GmStatus as status where status.id.hostname=:hostname and DATE(status.id.id)=:date order by status.id.id asc";
		Query query = session.createQuery(hsql);
		query.setString("hostname", hostname);
		query.setString("date", date);
		session.getTransaction().commit();
		ArrayList<Object> list = new ArrayList<Object>();
		list = (ArrayList<Object>) query.list();
		logger.info("return list size " + list.size());
		
		session.close();
		return list;
	}
	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}

}
