package pw.komarov.taxi.persistence.services;

import java.util.List;

import pw.komarov.taxi.persistence.entity.EntityIntf;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;

public interface EntityService<E extends EntityIntf> {
	E getEntity(Integer id);
	List<E> getAllEntities();
	void save(E entity) throws EntityObjectException;
	void delete(Integer id) throws EntityObjectException;
}