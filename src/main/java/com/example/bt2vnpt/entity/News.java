package com.example.bt2vnpt.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "NEWS")

public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    private boolean status;
    @Column(columnDefinition = "CLOB")
    private String content;
    private int newsType;
//    private String quantity;
    private String keyword;
    private String description;
    private int filterType;
    private String image;
    private String author;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Date createDate;
    @PrePersist
    private void onCreate(){
        createDate = new Date();
    }

    @ManyToOne
//    @JoinColumn(name = "CATEGORY_ID")
    private Category category;


    public News() {}



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

//    public Category getCategoryEntity() {
//        return category;
//    }
//
//    public void setCategoryEntity(Category category) {
//        this.category = category;
//    }
}
