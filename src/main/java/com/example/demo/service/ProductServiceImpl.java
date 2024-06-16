package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository; //made final

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = (List<Product>) productRepository.findAll();
        products.forEach(product -> System.out.println("Fetched product: " + product.getName() + ", ID: " + product.getId()));
        return products;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> listAll(String keyword){
        if(keyword !=null){
            return productRepository.search(keyword);
        }
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = findById(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found");
        }
        // Update properties of existing product with the new values

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setMaxInv(updatedProduct.getMaxInv());
        existingProduct.setMinInv(updatedProduct.getMinInv());
        existingProduct.setInv(updatedProduct.getInv());
        existingProduct.setPrice(updatedProduct.getPrice());
        productRepository.save(existingProduct);
    }
}
