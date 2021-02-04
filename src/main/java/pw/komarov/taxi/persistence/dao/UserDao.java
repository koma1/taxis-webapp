package pw.komarov.taxi.persistence.dao;

import pw.komarov.taxi.persistence.entity.UserEntity;

public class UserDao extends AbstractDao<UserEntity, Integer> {
	public UserDao() {
		super(UserEntity.class);
	}
	
	public UserEntity getByLogin(String login) {
		return getByParamValue("login", login);
	}
}