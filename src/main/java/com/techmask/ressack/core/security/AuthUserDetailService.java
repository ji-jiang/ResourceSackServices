package com.techmask.ressack.core.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.repository.UserRepository;

@Repository
public class AuthUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private UserDetails userDetails;

	public UserDetails loadUserByUsername(String tokenKey) throws UsernameNotFoundException {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		System.out.println("tokenKey"+tokenKey);
		System.out.println("11111111111111");
		
		
		User user = null;
		try {
			user = userRepository.loadUserByTokenKey(tokenKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("22222222222");
		System.out.println(user);

		userDetails = new org.springframework.security.core.userdetails.User(user.getTokenKey(), user.getTokenKey(),
				enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user));

		return userDetails;
	}

	protected List<GrantedAuthority> getAuthorities(User user) {
		String role = user.getRole();

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(role));

		return authList;
	}
}
