package dao;
import java.time.LocalDate;
import java.util.Collection;

// Generated Nov 26, 2019, 5:20:58 PM by Hibernate Tools 5.4.3.Final
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.Mission;
import model.Users;
import model.Mission;

/**
 * Home object for domain model class Mission.
 * @see dao.Mission
 * @author Hibernate Tools
 */
public class MissionHome {

	private static final Log log = LogFactory.getLog(MissionHome.class);

	private final SessionFactory sessionFactory = HibernateCommons.getInstance().getSessionFactory();

	

	public void persist(Mission transientInstance) {
		log.debug("persisting Mission instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Mission instance) {
		log.debug("attaching dirty Mission instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Mission instance) {
		log.debug("attaching clean Mission instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Mission persistentInstance) {
		log.debug("deleting Mission instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Mission merge(Mission detachedInstance) {
		log.debug("merging Mission instance");
		try {
			Mission result = (Mission) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Mission findById(long id) {
		log.debug("getting Mission instance with id: " + id);
		try {
			Mission instance = (Mission) sessionFactory.getCurrentSession().get(Mission.class, id);
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

		public Collection<Mission> findByUserId(String userid) {
		log.debug("getting Users instance with userid: " + userid);
		try {
			Session session = sessionFactory.getCurrentSession();
			Mission d = new Mission();
			UsersHome usrH  = new UsersHome();
			Users u = usrH.findById(Long.parseLong(userid));
			Query<Mission> hqlQuery;
			if(u.getIsemployeur()) {
				d.setEmployeur(u.getEmployeur());
				hqlQuery = session.createQuery("from Mission as dsp where dsp.employeur = :employeur",Mission.class);
			}else {
				d.setMedecinremp(u.getMedecinremp());
				hqlQuery = session.createQuery("from Mission as dsp where dsp.medecinremp = :medecinremp",Mission.class);
			}
			/* JavaBean in HQL */
		
			Collection<Mission> instance = hqlQuery.setProperties(d).getResultList(); //assumes javaBean has getName() & getAuthor() methods.
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
		
		public Collection<Mission> findByDate(LocalDate date) {
			log.debug("getting Users instance with userid: " + date);
			try {
				Session session = sessionFactory.getCurrentSession();
				Mission d = new Mission();
				Query<Mission> hqlQuery;
				d.setPeriode(date);
				hqlQuery = session.createQuery("from Mission as dsp where dsp.periode >= :periode",Mission.class);
				/* JavaBean in HQL */
				Collection<Mission> instance = hqlQuery.setProperties(d).getResultList(); //assumes javaBean has getName() & getAuthor() methods.
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
