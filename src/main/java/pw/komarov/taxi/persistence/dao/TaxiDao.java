package pw.komarov.taxi.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;

import lombok.Cleanup;
import pw.komarov.taxi.persistence.entity.TaxiEntity;
import pw.komarov.taxi.utils.CriteriaBuilder;
import pw.komarov.taxi.utils.HibernateUtils;

public class TaxiDao extends AbstractDao<TaxiEntity, Integer> {
	public TaxiDao() {
		super(TaxiEntity.class);
	}
	
	public TaxiEntity getByName(String name) {
		return getByParamValue("name", name);
	}
	
	public TaxiEntity getByPhone(String phone) {
		return getByParamValue("phone", phone);
	}
	
	@SuppressWarnings("unchecked")
	public List<TaxiEntity> findByParams(String name, String phone, String city, String text) {
		@Cleanup Session session = HibernateUtils.openSession();		
		
		// ToDo: (refact) Do inject this as builder functionality
		List<Criterion> criterias = new ArrayList<>();
		if(text != null) {
			criterias.add(CriteriaBuilder.getIlike("name", text));
			criterias.add(CriteriaBuilder.getIlike("phone", text));
			criterias.add(CriteriaBuilder.getIlike("citiesAlias.name", text));
		}
		
		Criteria criteria = session.createCriteria(TaxiEntity.class);
		if(city != null || text != null)
			criteria.createAlias("cities", "citiesAlias", JoinType.LEFT_OUTER_JOIN);
			
		CriteriaBuilder cb = new CriteriaBuilder(criteria)
				.addIlike("name", name)
				.addIlike("phone", phone)
				.addIlike("citiesAlias.name", city)
				.addOr(criterias) //...adds criterias if it not empty
			;
		
		return //ToDo: (fix) - Remove Stream.distinct()
			(List<TaxiEntity>) cb.build().list().stream().distinct().collect(Collectors.toList());
	}
}