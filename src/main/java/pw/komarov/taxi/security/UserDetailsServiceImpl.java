package pw.komarov.taxi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
//		UserEntity user = new UserDao();
//		
//		return new org.springframework.security.core.userdetails.User(
//		          user.getEmail(), user.getPassword(), user.isEnabled(), true, true, 
//		          true, getAuthorities(user.getRoles()));
	}

}
