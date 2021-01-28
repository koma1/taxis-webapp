import pw.komarov.taxi.persistence.services.*;
import pw.komarov.taxi.utils.HibernateUtils;

public class Runner {
	public static void main(String[] args) {
		EntityService<?> service = new TaxiService();
		
		Object o = service.getAllEntities();
		
		System.out.println("object: " + o);
		
		HibernateUtils.closeSessionFactory();
	}
}