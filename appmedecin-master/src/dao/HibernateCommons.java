package dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Dispotravail;
import model.Employeur;
import model.Medecinremp;
import model.Mission;
import model.Specialite;
import model.Users;

public class HibernateCommons {
	private static final Log log = LogFactory.getLog(HibernateCommons.class);
	private final SessionFactory sessionFactory;
	private final Session session;
	private static volatile HibernateCommons instance = null;
	
	private HibernateCommons() {
		super();
		Configuration conf = new Configuration().configure();
		conf.addAnnotatedClass(Users.class);
		conf.addAnnotatedClass(Dispotravail.class);
		conf.addAnnotatedClass(Employeur.class);
		conf.addAnnotatedClass(Medecinremp.class);
		conf.addAnnotatedClass(Mission.class);
		conf.addAnnotatedClass(Specialite.class);
		sessionFactory = conf.buildSessionFactory();
		session = sessionFactory.openSession();		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	 /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static HibernateCommons getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (HibernateCommons.instance == null) {
           // Le mot-clé synchronized sur ce bloc empêche toute instanciation
           // multiple même par différents "threads".
           // Il est TRES important.
           synchronized(HibernateCommons.class) {
             if (HibernateCommons.instance == null) {
               HibernateCommons.instance = new HibernateCommons();
             }
           }
        }
        return HibernateCommons.instance;
    }
}
