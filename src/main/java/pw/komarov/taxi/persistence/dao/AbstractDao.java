package pw.komarov.taxi.persistence.dao;

import java.util.List;
import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import lombok.Cleanup;
import pw.komarov.taxi.utils.HibernateUtils;
import pw.komarov.taxi.persistence.entity.EntityIntf;
import pw.komarov.taxi.persistence.exceptions.EntityObjectPersistException;

public abstract class AbstractDao<E extends EntityIntf, K extends Serializable> implements Dao<E, K> {
	private final Class<E> entityClass;
	
	AbstractDao(final Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	@SuppressWarnings("unchecked")
	E getByParamValue(String paramName, String paramValue) { //ToDo: optimize - rewrite to 'exists'
		@Cleanup Session session = HibernateUtils.openSession();
		Criteria criteria = session.createCriteria(entityClass);
		criteria.setMaxResults(1);
		criteria.add(Restrictions.eq(paramName, paramValue));
		
		List<?> list = criteria.list();
		
		return list.isEmpty() ? null : (E)list.get(0);
	}

	@Override
	public E findById(K id) {
		@Cleanup Session session = HibernateUtils.openSession();
		
		return session.get(entityClass, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		@Cleanup Session session = HibernateUtils.openSession();
		
		return session.createCriteria(entityClass).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public K create(E entity) throws EntityObjectPersistException {
		try(Session session = HibernateUtils.openSession()) {
			Transaction tr = session.beginTransaction();
			try {
				boolean error = false;
				try {
					session.save(entity);
				} catch(Exception e) {
					error = true;
					
					throw e;
				} finally {
					if(error)
						tr.rollback();
					else
						tr.commit();
				}
			} catch (Exception e) {
				if(e instanceof ConstraintViolationException)
					throw new EntityObjectPersistException(((ConstraintViolationException)e).getSQLException().getMessage(), e);
				else
					throw new EntityObjectPersistException(e);
			}
	
			return (K)entity.getId(); //ToDo: really unsafe cast
		}
	}

	@Override
	public void update(E entity) throws EntityObjectPersistException {
		try(Session session = HibernateUtils.openSession()) {
			Transaction tr = session.beginTransaction();
			try {
				boolean error = false;
				try {
					session.update(entity);
				} catch(Exception e) {
					error = true;
					
					throw e;
				} finally {
					if(error)
						tr.rollback();
					else
						tr.commit();
				}
			} catch (Exception e) {
				if(e instanceof ConstraintViolationException)
					throw new EntityObjectPersistException(((ConstraintViolationException)e).getSQLException().getMessage(), e);
				else
					throw new EntityObjectPersistException(e);
			}
		}
	}

	@Override
	public void delete(E entity) throws EntityObjectPersistException {
		try(Session session = HibernateUtils.openSession()) {
			Transaction tr = session.beginTransaction();
			try {
				boolean error = false;
				try {
					session.delete(entity);
				} catch(Exception e) {
					error = true;
	
					throw e;
				} finally {
					if(error)
						tr.rollback();
					else
						tr.commit();
				}
			}  catch (Exception e) {
				if(e instanceof ConstraintViolationException)
					throw new EntityObjectPersistException(((ConstraintViolationException)e).getSQLException().getMessage(), e);
				else
					throw new EntityObjectPersistException(e);
			}
		}
	}
}
