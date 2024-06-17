package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;


public interface ProductService {
    public List<Product> findAll();
    public Product findById(Long id);
    public void save (Product product);
    public void deleteById(Long id);
    void updateProduct(Long id, Product updatedProduct); // Added this method
    public List<Product> listAll(String keyword);

}
