package pw.komarov.taxi.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private HibernateUtils() {}
	
	private static SessionFactory sessionFactory;
   
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
        	Configuration configuration = new Configuration().configure();

        	sessionFactory = configuration.buildSessionFactory();                
        }
        
        return sessionFactory;
    }
    
    public static Session openSession() {
    	return getSessionFactory().openSession();
    }
    
    public static void closeSessionFactory() {
    	sessionFactory.close();
    	
    	sessionFactory = null;
    }
}