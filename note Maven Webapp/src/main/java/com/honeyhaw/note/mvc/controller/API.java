package com.honeyhaw.note.mvc.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honeyhaw.note.mvc.service.NoteService;

@Controller
@RequestMapping(value = "/api")
public class API {

	private final String version = "1.0";

	private NoteService noteService;

	public NoteService getNoteService() {
		return noteService;
	}

	@Resource
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	@RequestMapping(value = version + "/{url:\\w{2,8}}.json", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> json(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String url)
			throws Exception {
		Map<String, Object> note = noteService.queryByURL(url);
		return note;
	}

	@RequestMapping(value = version + "/{url:\\w{2,8}}.txt", method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	public @ResponseBody
	String text(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String url) throws Exception {
		Map<String, Object> note = noteService.queryByURL(url);
		return note.get("content") + "";
	}
	
	@RequestMapping(value = version + "/{url:\\w{2,8}}.md", method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	public @ResponseBody
	String markdown(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String url) throws Exception {
		Map<String, Object> note = noteService.queryByURL(url);
		return note.get("content") + "";
	}
}
