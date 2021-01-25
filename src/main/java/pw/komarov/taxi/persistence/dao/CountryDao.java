package pw.komarov.taxi.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import lombok.Cleanup;
import pw.komarov.taxi.persistence.entity.CityEntity;
import pw.komarov.taxi.persistence.entity.CountryEntity;
import pw.komarov.taxi.utils.CriteriaBuilder;
import pw.komarov.taxi.utils.HibernateUtils;

public class CountryDao extends AbstractDao<CountryEntity, Integer> {
	public CountryDao() {
		super(CountryEntity.class);
	}
	
	public CountryEntity getByName(String name) {
		return getByParamValue("name", name);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityEntity> getHeldBy(Integer countryId) {
		@Cleanup Session session = HibernateUtils.openSession();
		
		Query query = session.createQuery("SELECT c FROM CityEntity c WHERE c.country.id = :id");
		query.setParameter("id", countryId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CountryEntity> findByParams(String name) {
		@Cleanup Session session = HibernateUtils.openSession();
		
		Criteria criteria = session.createCriteria(CountryEntity.class);
			
		CriteriaBuilder cb = new CriteriaBuilder(criteria)
				.addIlike("name", name)
			;
		
		return //ToDo: (fix) - Remove Stream.distinct() - need normally HQL (JPQL/SQL) build
			(List<CountryEntity>) cb.build().list();
	}
}
