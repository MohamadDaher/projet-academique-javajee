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
import model.Employeur;
import model.Users;

/**
 * Home object for domain model class Employeur.
 * @see dao.Employeur
 * @author Hibernate Tools
 */
public class EmployeurHome {

	private static final Log log = LogFactory.getLog(EmployeurHome.class);

	private final SessionFactory sessionFactory = HibernateCommons.getInstance().getSessionFactory();

	

	public void persist(Employeur transientInstance) {
		log.debug("persisting Employeur instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Employeur instance) {
		log.debug("attaching dirty Employeur instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Employeur instance) {
		log.debug("attaching clean Employeur instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Employeur persistentInstance) {
		log.debug("deleting Employeur instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Employeur merge(Employeur detachedInstance) {
		log.debug("merging Employeur instance");
		try {
			Employeur result = (Employeur) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Employeur findById(long id) {
		log.debug("getting Employeur instance with id: " + id);
		try {
			Employeur instance = (Employeur) sessionFactory.getCurrentSession().get("dao.Employeur", id);
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

	public Collection<Employeur> findByUserId(String userid) {
		log.debug("getting Users instance with userid: " + userid);
		try {
			Session session = sessionFactory.getCurrentSession();
			Employeur d = new Employeur();
			UsersHome usrH  = new UsersHome();
			d.setUsers(usrH.findById(Long.parseLong(userid)));
			/* JavaBean in HQL */
			Query<Employeur> hqlQuery = session.createQuery("from Employeur as dsp where dsp.users = :users",Employeur.class);
			Collection<Employeur> instance = hqlQuery.setProperties(d).getResultList(); //assumes javaBean has getName() & getAuthor() methods.
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
