package com.honeyhaw.note.mvc.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honeyhaw.note.mvc.service.NoteService;

@Controller
@RequestMapping(value = "/")
public class Web{
	
	@Resource
	private NoteService noteService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String slug = noteService.createUniqueSlug();
		
		response.sendRedirect(slug);
	}

	@RequestMapping(value = "{url:\\w{2,8}}", method = RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String url) throws Exception {
		
		Map<String, Object> note = noteService.queryByURL(url);
		if( note == null ){
			return edit(request, response, url);
		}
		
		request.setAttribute("note", note);
		
		return "note";
	}

	private String edit(HttpServletRequest request,
			HttpServletResponse response, String url) {
		return "edit";
	}
}
