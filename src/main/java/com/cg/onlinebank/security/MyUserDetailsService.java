package com.cg.onlinebank.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.onlinebank.dao.UserDetailsDao;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserDetailsDao userDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.cg.onlinebank.bean.UserDetails> user = userDao.findByUserName(username);
		
		user.orElseThrow(()->new UsernameNotFoundException(username + "Not found"));
		return user.map(MyUserDetails::new).get();
	}

}
