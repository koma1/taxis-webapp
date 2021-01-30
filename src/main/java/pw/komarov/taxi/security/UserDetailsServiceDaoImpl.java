package pw.komarov.taxi.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pw.komarov.taxi.persistence.entity.UserEntity;
import pw.komarov.taxi.persistence.services.UserService;

public class UserDetailsServiceDaoImpl implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		UserEntity user = new UserService().getByLogin(username);
		if(user == null)
			throw new UsernameNotFoundException("Login not found: " + username);
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		user.getPrivileges().forEach((privilege) -> authorities.add(new SimpleGrantedAuthority(privilege.getName())));
		
		return new org.springframework.security.core.userdetails.User
				(user.getLogin(), user.getPassword(), authorities);
	}
}