package pw.komarov.taxi.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.sql.JoinType;
import org.hibernate.Criteria;
import org.hibernate.Query;

import lombok.Cleanup;
import pw.komarov.taxi.persistence.entity.CityEntity;
import pw.komarov.taxi.persistence.entity.TaxiEntity;
import pw.komarov.taxi.utils.CriteriaBuilder;
import pw.komarov.taxi.utils.HibernateUtils;

public class CityDao extends AbstractDao<CityEntity, Integer> {
	public CityDao() {
		super(CityEntity.class);
	}
	
	public CityEntity getByName(String name) {
		return getByParamValue("name", name);
	}
	
	@SuppressWarnings("unchecked")
	public List<TaxiEntity> getHeldBy(Integer cityId) {
		@Cleanup Session session = HibernateUtils.openSession();
		
		Query query = session.createQuery("SELECT t FROM TaxiEntity t JOIN t.cities c WHERE c.id = :id");
		query.setParameter("id", cityId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CityEntity> findByParams(String name, String countryName, Integer countryId) {
		@Cleanup Session session = HibernateUtils.openSession();
		
		Criteria criteria = session.createCriteria(CityEntity.class);
		if(countryName != null || countryId != null)
			criteria.createAlias("countries", "countriesAlias", JoinType.LEFT_OUTER_JOIN);
			
		CriteriaBuilder cb = new CriteriaBuilder(criteria)
				.addIlike("name", name)
				.addIlike("countriesAlias.name", countryName)
				.addEq("countriesAlias.id", countryId)
			;
		
		return //ToDo: (fix) - Remove Stream.distinct() - need normally HQL (JPQL/SQL) build
			(List<CityEntity>) cb.build().list().stream().distinct().collect(Collectors.toList());
	}
}