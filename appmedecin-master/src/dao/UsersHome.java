package dao;
// Generated Nov 26, 2019, 5:20:58 PM by Hibernate Tools 5.4.3.Final

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Users;


/**
 * Home object for domain model class Users.
 * @see dao.Users
 * @author Hibernate Tools
 */
public class UsersHome {

	public UsersHome() {
	}

	private static final Log log = LogFactory.getLog(UsersHome.class);

	private final SessionFactory sessionFactory = HibernateCommons.getInstance().getSessionFactory();
	
	
	public void persist(Users transientInstance) {
		log.debug("persisting Users instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Users findById(long id) {
		log.debug("getting Users instance with id: " + id);
		try {
			Users instance = (Users) sessionFactory.getCurrentSession().get(Users.class, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public Users findByMail(String mail) {
		log.debug("getting Users instance with mail: " + mail);
		try {
			Session session = sessionFactory.getCurrentSession();
			Users u = new Users();
			u.setMail(mail);
			/* JavaBean in HQL */
			Query<Users> hqlQuery = session.createQuery("from Users as usr where usr.mail = :mail",Users.class);
			Users instance = hqlQuery.setProperties(u).uniqueResult(); //assumes javaBean has getName() & getAuthor() methods.
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public Users findByPhone(long phone) {
		log.debug("getting Users instance with phone: " + phone);
		try {
			Session session = sessionFactory.getCurrentSession();
			Users u = new Users();
			u.setTelephone(phone);
			/* JavaBean in HQL */
			Query<Users> hqlQuery = session.createQuery("from Users as usr where usr.telephone = :telephone",Users.class);
			Users instance = hqlQuery.setProperties(u).uniqueResult(); //assumes javaBean has getName() & getAuthor() methods.
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
