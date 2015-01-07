package com.honeyhaw.note.mvc.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.honeyhaw.note.mvc.mappers.NoteMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/context-application.xml")
public class TestSimpleMyBatis {
	@Autowired
	private NoteMapper noteMapper;

	@Test
	public void findAll() {
		List<Map<String, Object>> note = noteMapper.selectAll();
		System.out.println(note);
	}
}