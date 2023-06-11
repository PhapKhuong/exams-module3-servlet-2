package com.service.impl;

import com.bean.Product;
import com.repository.impl.ProductRepositoryImpl;
import com.repository.itf.ProductRepository;
import com.service.itf.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository repository = new ProductRepositoryImpl();
    @Override
    public List<Product> display() {
        return repository.display();
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public void create(Product product) {
        repository.create(product);
    }

    @Override
    public List<Product> search(String str) {
        return repository.search(str);
    }

    @Override
    public void edit(Product product) {
        repository.edit(product);
    }
}
