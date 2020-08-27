package dao;
// Generated Nov 26, 2019, 5:20:58 PM by Hibernate Tools 5.4.3.Final

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Dispotravail;
import model.Users;

/**
 * Home object for domain model class Dispotravail.
 * @see dao.Dispotravail
 * @author Hibernate Tools
 */
public class DispotravailHome {

	private static final Log log = LogFactory.getLog(DispotravailHome.class);

	private final SessionFactory sessionFactory = HibernateCommons.getInstance().getSessionFactory();

	

	public void persist(Dispotravail transientInstance) {
		log.debug("persisting Dispotravail instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Dispotravail instance) {
		log.debug("attaching dirty Dispotravail instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dispotravail instance) {
		log.debug("attaching clean Dispotravail instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Dispotravail persistentInstance) {
		log.debug("deleting Dispotravail instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dispotravail merge(Dispotravail detachedInstance) {
		log.debug("merging Dispotravail instance");
		try {
			Dispotravail result = (Dispotravail) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Dispotravail findById(long id) {
		log.debug("getting Dispotravail instance with id: " + id);
		try {
			Dispotravail instance = (Dispotravail) sessionFactory.getCurrentSession().get(Dispotravail.class, id);
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
	
	public Collection<Dispotravail> findByUserId(String userid) {
		log.debug("getting Users instance with userid: " + userid);
		try {
			Session session = sessionFactory.getCurrentSession();
			Dispotravail d = new Dispotravail();
			UsersHome usrH  = new UsersHome();
			d.setUsers(usrH.findById(Long.parseLong(userid)));
			/* JavaBean in HQL */
			Query<Dispotravail> hqlQuery = session.createQuery("from Dispotravail as dsp where dsp.users = :users",Dispotravail.class);
			Collection<Dispotravail> instance = hqlQuery.setProperties(d).getResultList(); //assumes javaBean has getName() & getAuthor() methods.
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
