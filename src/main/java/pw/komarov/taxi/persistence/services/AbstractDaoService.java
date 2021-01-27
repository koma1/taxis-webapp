package pw.komarov.taxi.persistence.services;

import java.util.List;

import pw.komarov.taxi.persistence.dao.AbstractDao;
import pw.komarov.taxi.persistence.entity.EntityIntf;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;

public abstract class AbstractDaoService<E extends EntityIntf> implements EntityService<E> {
	private final AbstractDao<E, Integer> dao;
	
	protected AbstractDaoService(AbstractDao<E, Integer> dao) {
		this.dao = dao;
	}

	@Override
	public E getEntity(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<E> getAllEntities() {
		return (List<E>)dao.getAll();
	}

	@Override
	public void save(E entity) throws EntityObjectException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Integer id) throws EntityObjectException {
		throw new UnsupportedOperationException();
	}
	
}