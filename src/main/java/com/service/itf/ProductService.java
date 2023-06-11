package com.service.itf;

import com.bean.Product;

import java.util.List;

public interface ProductService {
    List<Product> display();

    void delete(String id);

    void create(Product product);

    List<Product> search(String str);

    void edit(Product product);
}
