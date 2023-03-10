package com.example.bt2vnpt.converter;

import com.example.bt2vnpt.DTO.NewsDTO;
import com.example.bt2vnpt.entity.News;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NewsConverter {
    public News toEntity(NewsDTO newsDTO){
        News entity = new News();
        entity.setTitle(newsDTO.getTitle());
        entity.setAuthor(newsDTO.getAuthor());
        entity.setContent(newsDTO.getContent());
        entity.setDescription(newsDTO.getDescription());
        entity.setFilterType(newsDTO.getFilterType());
        entity.setNewsType(newsDTO.getNewsType());
        entity.setImage(newsDTO.getImage());
        entity.setKeyword(newsDTO.getKeyword());
        entity.setStatus(newsDTO.isStatus());
        return entity;
    }
    public NewsDTO toDTO(News news){
        NewsDTO newsDto = new NewsDTO();
        if(news.getId() != null){
            newsDto.setId(news.getId());
        }
        newsDto.setTitle(news.getTitle());
        newsDto.setAuthor(news.getAuthor());
        newsDto.setContent(news.getContent());
        newsDto.setDescription(news.getDescription());

        newsDto.setFilterType(news.getFilterType());
        newsDto.setNewsType(news.getNewsType());
        newsDto.setImage(news.getImage());
        newsDto.setKeyword(news.getKeyword());
        newsDto.setStatus(news.isStatus());
        newsDto.setCreateDate(news.getCreateDate());
//        newsDto.setCategoryId(news.getCategoryEntity().getId());
//        newsDto.setModifiedDate(newsEntity.getModifiedDate());
        return newsDto;
    }
    public NewsDTO toDTO1(News news){
        NewsDTO newsDto = new NewsDTO();
        if(news.getId() != null){
            newsDto.setId(news.getId());
        }
        newsDto.setTitle(news.getTitle());
        newsDto.setAuthor(news.getAuthor());
        newsDto.setContent(news.getContent());
        newsDto.setDescription(news.getDescription());
        newsDto.setFilterType(news.getFilterType());
        newsDto.setNewsType(news.getNewsType());
        newsDto.setImage(news.getImage());
        newsDto.setKeyword(news.getKeyword());
        newsDto.setStatus(news.isStatus());
        newsDto.setCreateDate(news.getCreateDate());
        if(news.getCategory() != null){
            newsDto.setCategoryId(news.getCategory().getId());
        }

//        newsDto.setModifiedDate(newsEntity.getModifiedDate());
        return newsDto;
    }
    public News toEntity(NewsDTO newsDTO, News entity){
        entity.setTitle(newsDTO.getTitle());
        entity.setAuthor(newsDTO.getAuthor());
        entity.setContent(newsDTO.getContent());
        entity.setDescription(newsDTO.getDescription());
        entity.setFilterType(newsDTO.getFilterType());
        entity.setNewsType(newsDTO.getNewsType());
        entity.setImage(newsDTO.getImage());
        entity.setKeyword(newsDTO.getKeyword());
        entity.setStatus(newsDTO.isStatus());
        return entity;
    }
}
