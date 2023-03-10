package com.example.bt2vnpt.Service.Impl;

import com.example.bt2vnpt.Service.ICategoryService;
import com.example.bt2vnpt.entity.Category;
import com.example.bt2vnpt.repository.CategoryRepository;
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
public class CategoryServicce implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public static int totalPage ;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }
    public boolean getCategoryById(Long id){
        return categoryRepository.existsById(id);
    }



    @Override
    public Category update(Category category) {
        Category oldCategory = categoryRepository.findById(category.getId());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
//        Category category = categoryRepository.findById(id).get();
        categoryRepository.deleteById(id);
    }
    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }



    public List<Category> getAllCategory(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<Category> pagedResult = categoryRepository.findAll(paging);
        totalPage = pagedResult.getTotalPages();
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Category>();
        }
    }

}
