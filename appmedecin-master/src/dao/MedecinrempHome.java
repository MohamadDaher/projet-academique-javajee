package dao;
// Generated Nov 26, 2019, 5:20:58 PM by Hibernate Tools 5.4.3.Final

import java.time.LocalDate;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Medecinremp;
import model.Mission;

/**
 * Home object for domain model class Medecinremp.
 * @see dao.Medecinremp
 * @author Hibernate Tools
 */
public class MedecinrempHome {

	private static final Log log = LogFactory.getLog(MedecinrempHome.class);

	private final SessionFactory sessionFactory = HibernateCommons.getInstance().getSessionFactory();

	

	public void persist(Medecinremp transientInstance) {
		log.debug("persisting Medecinremp instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Medecinremp instance) {
		log.debug("attaching dirty Medecinremp instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Medecinremp instance) {
		log.debug("attaching clean Medecinremp instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Medecinremp persistentInstance) {
		log.debug("deleting Medecinremp instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Medecinremp merge(Medecinremp detachedInstance) {
		log.debug("merging Medecinremp instance");
		try {
			Medecinremp result = (Medecinremp) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Medecinremp findById(long id) {
		log.debug("getting Medecinremp instance with id: " + id);
		try {
			Medecinremp instance = (Medecinremp) sessionFactory.getCurrentSession().get("dao.Medecinremp", id);
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
