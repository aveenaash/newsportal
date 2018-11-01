package com.cubic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.dtos.NewsDTO;
import com.cubic.dtos.PartnerNewsDTO;
import com.cubic.services.NewsService;

@RestController
@RequestMapping(value = "/partner/news")
public class PartnerNewsController {

	@Autowired
	private NewsService newService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void createNews(@RequestBody PartnerNewsDTO newsDTO) {
		newService.savePartnerNews(newsDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PartnerNewsDTO getNews(@PathVariable(value = "id") final Long id, 
																@RequestParam(value = "partnerId") final Long partnerId) {
		return newService.getPartnerNewsById(partnerId,id);
	}
//	
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public List<NewsDTO> getNewsForCreator(@RequestParam(value = "creator") String creator) {
//		return newService.getAllNewsForCreator(creator);
//	}
}
