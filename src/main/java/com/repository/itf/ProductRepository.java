package com.repository.itf;

import com.bean.Product;

import java.util.List;


public interface ProductRepository {
    List<Product> display();

    void delete(String id);

    void create (Product product);

    List<Product> search(String str);

    void edit(Product product);
}
