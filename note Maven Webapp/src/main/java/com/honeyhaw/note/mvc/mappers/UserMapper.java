package com.honeyhaw.note.mvc.mappers;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserMapper {
	public Map<String, Object> query(int id);

	public Map<String, Object> queryByUsername(String username);

	public int add(Map<String, Object> user);
}
