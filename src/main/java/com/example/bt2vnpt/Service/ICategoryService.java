package com.example.bt2vnpt.Service;

import com.example.bt2vnpt.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Category save(Category category);
    Optional<Category> getById(Long id);
    Category update(Category category);
    void delete(Long id);

    List<Category> getAllCategory(Integer pageNo, Integer pageSize, String sortBy);
}
