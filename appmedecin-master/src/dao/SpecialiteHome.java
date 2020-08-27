package dao;
// Generated Nov 26, 2019, 5:20:58 PM by Hibernate Tools 5.4.3.Final

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import model.Specialite;

/**
 * Home object for domain model class Specialite.
 * @see dao.Specialite
 * @author Hibernate Tools
 */
public class SpecialiteHome {

	private static final Log log = LogFactory.getLog(SpecialiteHome.class);

	private final SessionFactory sessionFactory = HibernateCommons.getInstance().getSessionFactory();

	

	public void persist(Specialite transientInstance) {
		log.debug("persisting Specialite instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Specialite instance) {
		log.debug("attaching dirty Specialite instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Specialite instance) {
		log.debug("attaching clean Specialite instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Specialite persistentInstance) {
		log.debug("deleting Specialite instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Specialite merge(Specialite detachedInstance) {
		log.debug("merging Specialite instance");
		try {
			Specialite result = (Specialite) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Specialite findById(long id) {
		log.debug("getting Specialite instance with id: " + id);
		try {
			Specialite instance = (Specialite) sessionFactory.getCurrentSession().get("dao.Specialite", id);
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
