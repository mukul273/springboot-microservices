package com.spring.boot.springbootmaster.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.boot.springbootmaster.model.User;

@Component
public class UserDao {

	private static List<User> users = new ArrayList<>();
	
	private int userCount = users.size();
	
	static {
		users.add(new User(1, "Chimp", new Date(), "address1"));
		users.add(new User(2, "Mrs.Chimp", new Date(), "Address2"));
		users.add(new User(3, "Ms.Chimp", new Date(), "address3"));
		users.add(new User(4, "LittleChimp", new Date(), "address4"));
	}
	
	public List<User> findAllUsers() {
		return users;
	}
	
	public User getUser(Integer id) {
		for(User user : users) {
			if(user.getId() == id)
				return user;
		}
		
		return null;
	}
	
	public User saveUser(User user) {
		
		if(user.getId()==null)
			user.setId(++userCount);
		
		users.add(user);
		return user;
	}
}
