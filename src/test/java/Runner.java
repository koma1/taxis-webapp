import pw.komarov.taxi.utils.HibernateUtils;

public class Runner {
	public static void main(String[] args) {	
		try {
			// Write your code here...
		} finally {
			HibernateUtils.closeSessionFactory();
		}
	}
}