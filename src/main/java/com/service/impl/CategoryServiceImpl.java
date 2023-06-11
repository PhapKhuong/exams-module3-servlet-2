package com.service.impl;

import com.bean.Category;
import com.repository.impl.CategoryRepositoryImpl;
import com.repository.itf.CategoryRepository;
import com.service.itf.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryRepository repository = new CategoryRepositoryImpl();
    @Override
    public List<Category> display() {
        return repository.display();
    }

    @Override
    public Category search(String id) {
        return repository.search(id);
    }
}
