package com.honeyhaw.note.mvc.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honeyhaw.note.mvc.mappers.UserMapper;

@Service
public class UserService {
	
	@Resource
	private UserMapper userMapper;
	
	public Map<String, Object> genUserForCookie(){
		String username = UUID.randomUUID().toString().replaceAll("-", "");
		String password = UUID.randomUUID().toString().replaceAll("-", "");
		
		Map<String, Object> user = new HashMap<String, Object>();
		
		user.put("username", username);
		user.put("password", password);
		user.put("source", "cookie");
		
		add( user );
		
		user.put("id",((Long) user.get("Id")).intValue());
		
		return user;
	}

	public int add(Map<String, Object> user) {
		user.put("create_at", new Timestamp( System.currentTimeMillis() ));
		return this.userMapper.add(user);
	}
	
	public Map<String, Object> query(int id){
		return this.userMapper.query(id);
	}
	
	public Map<String, Object> verify(String username, String password){
		Map<String, Object> user = this.userMapper.queryByUsername(username);
		
		if( user == null ){
			return null;
		}
		
		if( password != null && password.equals(user.get("password"))){
			return user;
		}else{
			return null;
		}
		
	}
}
