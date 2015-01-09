package com.honeyhaw.note.mvc.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honeyhaw.note.mvc.service.NoteService;
import com.honeyhaw.note.mvc.service.UserService;

@Controller
@RequestMapping(value = "/api")
public class API {

	private final String version = "1.0";
	
	@Resource
	private NoteService noteService;
	
	@Resource
	private UserService userService;

	@RequestMapping(value = version + "/{url:\\w{2,8}}", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> put(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String url,
			@RequestParam(required = false, defaultValue="0") int id,
			@RequestParam String content,
			@RequestParam(required = false) String language,
			@CookieValue(required = false) String username,
			@CookieValue(required = false) String password) throws Exception {
		Map<String, Object> note = noteService.queryByURL(url);
		
		boolean newUser = false;
		Map<String, Object> user = null;
		
		if( username == null && password == null ){
			newUser = true;
		}else{
			user = this.userService.verify(username, password);
		}
		
		if( newUser || user == null ){
			user = userService.genUserForCookie();
			Cookie usernameCookie = new Cookie("username", (String)user.get("username"));
			Cookie passwordCookie = new Cookie("password", (String)user.get("password"));
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);
			newUser = true;
		}
		
		boolean editable = false;
		
		if( note == null ){
			note = this.noteService.createNote(url,(Integer) user.get("id"));
			editable = true;
		}else{
			
			if( newUser ){
				editable = false;
			}else{
				int owner = (Integer) note.get("owner");
				if( (Integer)user.get("id") == owner ){
					editable = true;
				}else{
					editable = false;
				}
			}
		}
		
		if( editable ){
			note.put("content", content);
			note.put("language", language);
			
			this.noteService.update(note);
		}
		
		return note;
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
		return note.get("raw") + "";
	}
	
	@RequestMapping(value = version + "/{url:\\w{2,8}}.md", method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
	public @ResponseBody
	String markdown(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String url) throws Exception {
		Map<String, Object> note = noteService.queryByURL(url, true);
		return note.get("content") + "";
	}
}
