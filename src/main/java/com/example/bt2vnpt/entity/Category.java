package com.example.bt2vnpt.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Cascade(value= {org.hibernate.annotations.CascadeType.ALL})
    private long id;
    private String name;
    private boolean status;
    private String description;

    @OneToMany( cascade =  {CascadeType.PERSIST},targetEntity = News.class)
    @JoinColumn(name = "CATEGORY_ID",referencedColumnName = "id")
    private List<News> newsList;

    public Category() {
    }

    public void setId(long id) {
        this.id = id;
    }

//    public Set<News> getNews() {
//        return news;
//    }
//
//    public void setNews(Set<News> news) {
//        this.news = news;
//    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
