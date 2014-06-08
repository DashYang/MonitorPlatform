package database.provider;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.apache.log4j.Logger;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static final ThreadLocal session = new ThreadLocal();
	static Logger logger = Logger.getLogger(HibernateUtil.class);

	static {
		try {
			// 创建hibernate会化工厂
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
			logger.info("数据库连接成功");
		} catch (Throwable ex) {
			logger.info("数据库连接失败" + ex.toString());
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() {
		Session s = (Session) session.get();
		// 开始会话
		s = sessionFactory.openSession();
		session.set(s);
		return s;
	}
}
