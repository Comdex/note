package com.honeyhaw.note.mvc.mappers;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NoteMapper {
	public Map<String, Object>  query(int id);
	public Map<String, Object>  queryByURL(String url);
	public Map<String, Object>  queryBySlug(String slug);
    public int add(Map<String, Object> note);
     
    public void updateNote(Map<String, Object> note);
     
    public void deleteNote(int id);
     
    public List<Map<String, Object>> selectAll();

	public Map<String, Object> selectNoteByAlias(String alias);
	public int update(Map<String, Object> note);
}
