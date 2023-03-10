package com.example.bt2vnpt.repository;

import com.example.bt2vnpt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> , PagingAndSortingRepository<Category, Long> {
    Category findById(long id);


}
