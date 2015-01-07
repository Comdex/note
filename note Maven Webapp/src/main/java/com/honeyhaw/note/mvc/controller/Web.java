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

import com.honeyhaw.note.mvc.service.NoteService;
import com.honeyhaw.note.mvc.service.UserService;

@Controller
@RequestMapping(value = "/")
public class Web{
	
	@Resource
	private NoteService noteService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String slug = noteService.createUniqueSlug();
		
		response.sendRedirect(slug);
	}

	@RequestMapping(value = "{url:\\w{2,8}}", method = RequestMethod.GET)
	public String view(HttpServletRequest request, HttpServletResponse response,@CookieValue(required=false) String username, @CookieValue(required=false) String password,
			@PathVariable String url) throws Exception {
		
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
		
		request.setAttribute("note", note);
		if( editable ){
			return "edit";
		}else{
			
			return "note";
		}
	}
}
