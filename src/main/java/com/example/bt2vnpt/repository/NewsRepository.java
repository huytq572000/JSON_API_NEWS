package com.example.bt2vnpt.repository;


import com.example.bt2vnpt.DTO.NewsDTO;

import com.example.bt2vnpt.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long>, PagingAndSortingRepository<News, Long> {
//  @Query(value = "select news0.title from News news0")
//  List<News> getList();
//@Query(value = "SELECT new News(e.id, e.content, e.author, e.title) FROM News e",nativeQuery = true)
//List<News> getList();
//@Query("SELECT  e.title, e.content, e.author FROM News e")
//List<Object> getList();
//    @Query("SELECT title,content FROM News")
//    List<NewsDTO> getList();
//  Page<News> findAll1(List<News> getList, Pageable paging1);
//    @Query (value = "SELECT  e.ID as id,e.TITLE as title, e.AUTHOR as author FROM News e",nativeQuery = true )
////    List<NewsInfoDTO> listNews();
//    Page<News> listNews(List<NewsInfoDTO> getList,Pageable pageable);
}
