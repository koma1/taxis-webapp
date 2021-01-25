package pw.komarov.taxi.utils;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

public class CriteriaBuilder {	
	private Criteria criteria;
	
	public CriteriaBuilder(Criteria criteria) {
		this.criteria = criteria;
	}
	
	public static Criterion getIlike(String name, String value) {
		if(value != null)
			if(value == "")
				return Restrictions.or(
						Restrictions.isNull(name),
						Restrictions.eq(name, ""));
			else
				return Restrictions.ilike(name, "%" + value + "%");
		
		return null;
	}
	
	public CriteriaBuilder addEq(String name, Object value) {
		if (value != null)
			criteria.add(Restrictions.eq(name, value));
		
		return this;
	}
	
	public CriteriaBuilder addIlike(String name, String value) {
		Criterion criterion = getIlike(name, value);
		if(criterion != null)
			criteria.add(criterion);
		
		return this;
	}
	
	public CriteriaBuilder addOr(List<Criterion> criterions) {
		Disjunction disjunction = Restrictions.disjunction();
		
		for(Criterion criterion : criterions) {
			if(criterion != null)
				disjunction.add(criterion);
	    }
		
		if(!criterions.isEmpty())
			criteria.add(disjunction);

		return this;
	}
	
	public Criteria build() {		
		return criteria;
	}
}
