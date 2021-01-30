import pw.komarov.taxi.persistence.services.*;
import pw.komarov.taxi.utils.HibernateUtils;

public class Runner {
	public static void main(String[] args) {	
		Object o = new TaxiService().getAllEntities();
		
		System.out.println("object: " + o);
		
		HibernateUtils.closeSessionFactory();
	}
}