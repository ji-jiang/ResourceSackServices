package com.startup.bookapp.core.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.startup.bookapp.core.util.CryptUtil;
import com.startup.bookapp.usermanager.domain.User;
import com.startup.bookapp.usermanager.repository.UserRepository;

@Repository
public class AuthUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;


	private UserDetails userDetails;
		
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
		
		User user = userRepository.findOneByEmail(email);
		System.out.println(user);
		System.out.println(CryptUtil.crypt(user.getPassword(), user.getEmail()));
		
		userDetails = new org.springframework.security.core.userdetails.User (user.getEmail(),
                user.getPassword(), 
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user)
                );
		
		return userDetails;
	}
	
	protected List<GrantedAuthority> getAuthorities(User user) {
	    String role = user.getRole();
	    
	    List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
	    authList.add(new SimpleGrantedAuthority(role));
	    
	    return authList;
	  }
}
