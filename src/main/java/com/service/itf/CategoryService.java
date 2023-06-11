package com.service.itf;

import com.bean.Category;

import java.util.List;

public interface CategoryService {
    List<Category> display();
    Category search(String id);
}
