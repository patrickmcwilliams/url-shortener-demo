package com.mcwilliams.urlshortener.rest;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcwilliams.urlshortener.dao.URLDao;

@RestController
public class RestService {
	
	@Autowired
	URLDao dao;
	
	@RequestMapping(value = "/x/{hash}", method = RequestMethod.GET )
	public void getFullURL(@PathVariable("hash") String hash, HttpServletResponse httpServletResponse) {
		String url = dao.getURLFromHash(hash);
		if (!url.equals("/404")) url = "//"+url;
	    httpServletResponse.setHeader("Location", url);
	    httpServletResponse.setStatus(302);
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.GET )
	public String getShortURL(@RequestParam("url") String url, HttpServletRequest request) throws MalformedURLException {
		url = url.replaceFirst("^(http[s]?://||//)", "");
		return "<a href='//"+request.getHeader("host")+"/x/"+dao.getHashFromURL(url)+"'>Link</a>";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET )
	public String notFound(HttpServletRequest request) {
		return "URL does not exist<br><a href='//"+request.getHeader("host")+"'>Home</a>";
	}
}
