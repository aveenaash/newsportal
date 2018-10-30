package com.cubic.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubic.dtos.NewsDTO;
import com.cubic.entities.NewsEntity;
import com.cubic.repositories.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	
	public void saveNews(NewsDTO news) {
		NewsEntity newsEntity = new NewsEntity();
		newsEntity.setTitle(news.getTitle());
		newsEntity.setContent(news.getContent());
		newsEntity.setCreatorName(news.getCreatorName());
		newsEntity.setTags(String.join(",", news.getTags()));
		newsEntity.setCreatedAt(new Date());
		
		newsRepository.save(newsEntity);
	}
	
	public NewsDTO getNewsById(long id) {
		NewsEntity  entity = newsRepository.findById(id);
		return convert(entity);
	}
	
	public List<NewsDTO> getAllNewsForCreator(String creator) {
		List<NewsEntity>  news = newsRepository.findByCreator(creator);
		List<NewsDTO> dtoList = new ArrayList<NewsDTO>();
		for(NewsEntity newsEntity : news) {
			dtoList.add(convert(newsEntity));
		}
		return dtoList;
	}
	
	private NewsDTO convert(NewsEntity entity) {
		NewsDTO dto = new NewsDTO();
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setCreatorName(entity.getCreatorName());
		dto.setTags(Arrays.asList(entity.getTags().split(",")));
		dto.setCreatedAt(entity.getCreatedAt());
		
		return dto;
	}
}
