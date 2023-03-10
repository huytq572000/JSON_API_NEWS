package com.example.bt2vnpt.Service;

import com.example.bt2vnpt.DTO.NewsDTO;

import com.example.bt2vnpt.DTO.NewsInfoDTO;
import com.example.bt2vnpt.entity.News;

import java.util.List;
import java.util.Optional;

public interface INewsService {
       NewsDTO save(NewsDTO newsDTO);
       Optional<NewsDTO> getById(Long id);
       NewsDTO update(NewsDTO newsDTO);
       void delete(Long id);

       List<NewsInfoDTO> getAllNews(Integer pageNo,Integer pageSize,String sortBy);
//       List<NewsInfoDTO> listNews();
}
