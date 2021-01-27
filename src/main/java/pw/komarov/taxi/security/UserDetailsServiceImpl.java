package pw.komarov.taxi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pw.komarov.taxi.persistence.entity.UserEntity;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
//		UserEntity user = new UserDao();
//		
//		return new org.springframework.security.core.userdetails.User(
//		          user.getEmail(), user.getPassword(), user.isEnabled(), true, true, 
//		          true, getAuthorities(user.getRoles()));
	}

}
