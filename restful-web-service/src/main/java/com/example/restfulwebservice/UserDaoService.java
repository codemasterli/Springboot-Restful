package com.example.restfulwebservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    
    private static int usersCount = 3;
    
    static {
    	users.add(new User(1,"Kenneth", new Date()));
    	users.add(new User(2,"Alice", new Date()));
    	users.add(new User(3,"Elena", new Date()));
    }
    
    public List<User> findAll(){
    	return users;
    }
    
    public User save(User user) {
    	if(user.getId() == null) {
    		user.setId(++usersCount);
    	}
    	users.add(user);
    	return user;
    }
    
    // 개별 사용자 조회
    public User findOne(int id) {
    	for (User user : users) {
    		if (user.getId() == id) {
    			return user;
    		}
    	}
    	return null;
    }
    
    public User deletebyId(int id) {
    	Iterator<User> iterator = users.iterator();
    	
    	while (iterator.hasNext()) { //하나씩 아이디가 저장
    		User user = iterator.next();
    		
    		if(user.getId() == id) {
    			iterator.remove();
    			return user;
    		}
    	}
    	return null;
    }
    
    public User updateUser(int id, User user) {
        User updateUser = findOne(id);

        if (findOne(id) != null) {
            updateUser.setName(user.getName());
            updateUser.setJoinDate(new Date());
            return updateUser;
        }
        return null;
    }
}
