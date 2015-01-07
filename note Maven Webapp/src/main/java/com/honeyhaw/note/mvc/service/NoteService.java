package com.honeyhaw.note.mvc.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.pegdown.PegDownProcessor;
import org.springframework.stereotype.Service;

import com.honeyhaw.note.mvc.mappers.NoteMapper;

@Service
public class NoteService {
	private static final String chars = "afhijkmnprtwxy3478";
	
	private NoteMapper noteMapper;

	public NoteMapper getNoteMapper() {
		return noteMapper;
	}
	
	/**
	 * 
	 */
	public String createUniqueSlug() {
		return createUniqueSlug(8);
	}
	
	/**
	 * 
	 * @param length
	 * @return
	 */
	public String createUniqueSlug( int length) {
		String slug = createRandomSlug(length);
		
		if( noteMapper.queryBySlug(slug) == null ){
			return slug;
		}else{
			return createUniqueSlug(length);
		}
	}
	
	/**
	 * 
	 * @param length
	 * @return
	 */
	public String createRandomSlug( int length) {
		int maxIndex = chars.length() - 1;
		char[] slugChars = new char[length];
		for( int i = 0; i< length; i++ ){
			int index = RandomUtils.nextInt(0, maxIndex);
			slugChars[i] = chars.charAt(index);
		}
		return new String(slugChars);
	}

	@Resource
	public void setNoteMapper(NoteMapper noteMapper) {
		this.noteMapper = noteMapper;
	}
	
	public Map<String, Object>  queryByURL( String url){
		Map<String, Object> note = noteMapper.queryByURL(url);
		
		if( note != null ){
			
			String content = (String) note.get("content");
			
			note.put("raw", content);
			
			PegDownProcessor processor = new PegDownProcessor();
			
			note.put("content", processor.markdownToHtml( content ));
		}
		
		return note;
	}
}
