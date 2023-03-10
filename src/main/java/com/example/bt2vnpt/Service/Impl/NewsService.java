package com.example.bt2vnpt.Service.Impl;

import com.example.bt2vnpt.DTO.NewsDTO;

import com.example.bt2vnpt.DTO.NewsInfoDTO;
import com.example.bt2vnpt.Service.INewsService;
import com.example.bt2vnpt.converter.NewsConverter;
import com.example.bt2vnpt.entity.Category;
import com.example.bt2vnpt.entity.News;
import com.example.bt2vnpt.repository.CategoryRepository;
import com.example.bt2vnpt.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class NewsService implements INewsService {

    public static boolean checkNewsId = false;
    public static int totalPage;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NewsConverter newsConverter;

    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        Category category = categoryRepository.findById((long) newsDTO.getCategoryId());
        News news = newsConverter.toEntity(newsDTO);
        news.setCategory(category);
        news = newsRepository.save(news);
        return newsConverter.toDTO(news);
    }

    @Override
    public Optional<NewsDTO> getById(Long id) {
        News news = newsRepository.findById(id).get();
        return Optional.ofNullable(newsConverter.toDTO1(news));
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        News oldNews = newsRepository.findById(newsDTO.getId()).get();
        if (oldNews != null) {
            checkNewsId = true;
        }
        News news = newsConverter.toEntity(newsDTO, oldNews);
        Category category = categoryRepository.findById((long) newsDTO.getCategoryId());
        news.setCategory(category);
        news = newsRepository.save(news);
        return newsConverter.toDTO(news);
    }

    @Override
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<NewsInfoDTO> getAllNews(Integer pageNo, Integer pageSize, String sortBy) {
        List<NewsInfoDTO> models = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("title").descending());
        Page<News> newsList = newsRepository.findAll(pageable);
        totalPage = newsList.getTotalPages();
        if(newsList.hasContent()) {
            newsList.getContent();
            for (News item : newsList) {
                NewsInfoDTO newsInfoDTO = new NewsInfoDTO();
                newsInfoDTO.setTitle(item.getTitle());
                newsInfoDTO.setNewsType(item.getNewsType());
                newsInfoDTO.setFilterType(item.getFilterType());
                newsInfoDTO.setId(item.getId());
                models.add(newsInfoDTO);
            }
            return models;
        }
        return null;
    }

    //    public List<News> getAllNews(Integer pageNo, Integer pageSize, String sortBy) {
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
//        Page<News> pagedResult = newsRepository.findAll(paging);
//        totalPage = pagedResult.getTotalPages();
//        if (pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<News>();
//        }
//    }
}




