package com.repository.itf;

import com.bean.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> display();

    Category search(String id);
}
