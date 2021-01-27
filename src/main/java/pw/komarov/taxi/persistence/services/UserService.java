package pw.komarov.taxi.persistence.services;

import java.util.List;

import pw.komarov.taxi.persistence.dao.UserDao;
import pw.komarov.taxi.persistence.entity.UserEntity;
import pw.komarov.taxi.persistence.exceptions.EntityObjectException;

public class UserService implements EntityService<UserEntity> {
	private final static UserDao dao = new UserDao();
	
	@Override
	public UserEntity getEntity(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<UserEntity> getAllEntities() {
		return dao.getAll();
	}

	@Override
	public void save(UserEntity entity) throws EntityObjectException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Integer id) throws EntityObjectException {
		throw new UnsupportedOperationException();
	}
}