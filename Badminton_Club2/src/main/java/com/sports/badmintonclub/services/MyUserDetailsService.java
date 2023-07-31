package com.sports.badmintonclub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sports.badmintonclub.models.User;
import com.sports.badmintonclub.repositories.UserRepository;
import com.sports.badmintonclub.security.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		//tacks the user object arguments values and create the MyUserdetails class object 
		return user.map(MyUserDetails::new).get();
	}
}
