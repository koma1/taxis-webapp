package pw.komarov.taxi.persistence.services;

import pw.komarov.taxi.persistence.dao.UserDao;
import pw.komarov.taxi.persistence.entity.UserEntity;

public class UserService extends AbstractDaoService<UserEntity> {
	private final static UserDao dao = new UserDao();
	
	public UserService() {
		super(dao);
	}
	
	public UserEntity getByLogin(String login) {
		return dao.getByLogin(login);
	}
}