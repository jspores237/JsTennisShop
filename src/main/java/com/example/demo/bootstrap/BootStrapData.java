package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BootStrapData(PartRepository partRepository, ProductRepository productRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("BootStrapData run method started");

        long partCount = partRepository.count();
        long productCount = productRepository.count();
        System.out.println("Parts count: " + partCount);
        System.out.println("Products count: " + productCount);

        if (partCount == 0 && productCount == 0) {
            initializeInventory();
        } else {
            System.out.println("Data already exists. No duplicates added!");
        }

        displayInventory();
    }

    @Transactional
    public void initializeInventory() {

        OutsourcedPart part1 = new OutsourcedPart("Polyester String", 16.49, 4, 100, 17, "Solinco");
        part1.setMinInv(4);
        part1.setMaxInv(100);
        part1.setInv(17);
        partRepository.save(part1);

        OutsourcedPart part2 = new OutsourcedPart("Natural Gut String", 23.99, 4, 100, 12, "Babolat");
        part2.setMinInv(4);
        part2.setMaxInv(100);
        part2.setInv(12);
        partRepository.save(part2);

        OutsourcedPart part3 = new OutsourcedPart("Dampener", 4.99, 4, 100, 91, "Babolat");
        part3.setMinInv(4);
        part3.setMaxInv(100);
        part3.setInv(91);
        partRepository.save(part3);

        OutsourcedPart part4 = new OutsourcedPart("Pro Overgrip", 6.49, 4, 100, 19, "Wilson");
        part4.setMinInv(4);
        part4.setMaxInv(100);
        part4.setInv(19);
        partRepository.save(part4);

        OutsourcedPart part5 = new OutsourcedPart("Tennis Balls 3 Pack", 7.49, 4, 100, 14, "Penn");
        part5.setMinInv(4);
        part5.setMaxInv(100);
        part5.setInv(14);
        partRepository.save(part5);


        // Initialize products
        Product product1 = new Product("104 sq inch Racket", 170.0, 2, 25, 10);
        product1.setMinInv(2);
        product1.setMaxInv(25);
        product1.setInv(10);
        productRepository.save(product1);

        Product product2 = new Product("100 sq inch Racket", 220.0, 2, 25, 12);
        product2.setMinInv(2);
        product2.setMaxInv(25);
        product2.setInv(12);
        productRepository.save(product2);

        Product product3 = new Product("98 sq inch Racket", 220.0, 2, 25, 5);
        product3.setMinInv(2);
        product3.setMaxInv(25);
        product3.setInv(5);
        productRepository.save(product3);

        Product product4 = new Product("97 sq inch Racket", 200.0, 2, 25, 7);
        product4.setMinInv(2);
        product4.setMaxInv(25);
        product4.setInv(7);
        productRepository.save(product4);

        Product product5 = new Product("95 sq inch Racket", 220.0, 2, 25, 11);
        product5.setMinInv(2);
        product5.setMaxInv(25);
        product5.setInv(11);
        productRepository.save(product5);
    }


    private void addPartIfUnique(OutsourcedPart part) {
        if (!partRepository.existsById(part.getPartId())) {
            partRepository.save(part);
            System.out.println("Saved part: " + part.getName());
        } else {
            System.out.println("Part already exists: " + part.getName());
        }
    }

    private void displayInventory() {
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println("Number of Parts: " + partRepository.count());

        partRepository.findAll().forEach(part -> {
            if (part != null) {
                System.out.println("Part fetched from DB: " + part.getName() + ", Min Inv: " + part.getMinInv() + ", Max Inv: " + part.getMaxInv() + ", Current Inv: " + part.getInv());
            } else {
                System.out.println("Null part found in inventory.");
            }
        });
        productRepository.findAll().forEach(product -> {
            if (product != null) {
                System.out.println("Product fetched from DB: " + product.getName() + ", Min Inv: " + product.getMinInv() + ", Max Inv: " + product.getMaxInv() + ", Current Inv: " + product.getInv());
            } else {
                System.out.println("Null product found in inventory.");
            }
        });
    }
}
