package pw.komarov.taxi.persistence.dao;

import java.util.List;

import pw.komarov.taxi.persistence.exceptions.EntityObjectPersistException;

interface Dao<E, K> {	
	E findById(K id);
	List<E> getAll();
	
	K create(E entity) throws EntityObjectPersistException;
	void update(E entity) throws EntityObjectPersistException;
	void delete(E entity) throws EntityObjectPersistException;
}