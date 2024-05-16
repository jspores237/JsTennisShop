package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (outsourcedPartRepository.count() == 0 && productRepository.count() == 0) {
            initializeInventory();
        } else {
            System.out.println("Data already exists. No duplicates added!");
        }

        displayInventory();
    }

    private void initializeInventory() {

        //Initialize parts
        OutsourcedPart part1 = new OutsourcedPart();
        part1.setName("Polyester String");
        part1.setPrice(16.49);
        part1.setMinInv(3);
        part1.setMaxInv(50);
        part1.setInv(17);
        part1.setCompanyName("Solinco");
        outsourcedPartRepository.save(part1);

        OutsourcedPart part2 = new OutsourcedPart();
        part2.setName("Natural Gut String");
        part2.setPrice(23.99);
        part2.setMinInv(3);
        part2.setMaxInv(50);
        part2.setInv(12);
        part2.setCompanyName("Babolat");
        outsourcedPartRepository.save(part2);

        OutsourcedPart part3 = new OutsourcedPart();
        part3.setName("Dampener");
        part3.setPrice(4.99);
        part3.setMinInv(10);
        part3.setMaxInv(100);
        part3.setInv(91);
        part3.setCompanyName("Babolat");
        outsourcedPartRepository.save(part3);

        OutsourcedPart part4 = new OutsourcedPart();
        part4.setName("Pro Overgrip");
        part4.setPrice(6.49);
        part4.setMinInv(5);
        part4.setMaxInv(50);
        part4.setInv(19);
        part4.setCompanyName("Wilson");
        outsourcedPartRepository.save(part4);

        OutsourcedPart part5 = new OutsourcedPart();
        part5.setName("Tennis Balls 3 Pack");
        part5.setPrice(7.49);
        part5.setMinInv(5);
        part5.setMaxInv(75);
        part5.setInv(14);
        part5.setCompanyName("Penn");
        outsourcedPartRepository.save(part5);


        //Initialize products
        Product product1 = new Product();
        product1.setName("104 sq inch Racket");
        product1.setPrice(170.0);
        product1.setInv(10);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("100 sq inch Racket");
        product2.setPrice(220.0);
        product2.setInv(12);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("98 sq inch Racket");
        product3.setPrice(220.0);
        product3.setInv(5);
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("97 sq inch Racket");
        product4.setPrice(200.0);
        product4.setInv(4);
        productRepository.save(product4);

        Product product5 = new Product();
        product5.setName("95 sq inch Racket");
        product5.setPrice(220.0);
        product5.setInv(11);
        productRepository.save(product5);
    }

    private void addPartIfUnique(OutsourcedPart part) {
        Set<OutsourcedPart> parts = new HashSet<>((List<OutsourcedPart>) outsourcedPartRepository.findAll());
        if (parts.add(part)) {
            outsourcedPartRepository.save(part);
        } else {
            // Handle as a multipack if duplicate
            part.setInv(part.getInv() * 2);
            outsourcedPartRepository.save(part);
        }
    }

    private void displayInventory() {
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println("Number of Parts: " + partRepository.count());
        outsourcedPartRepository.findAll().forEach(part -> System.out.println(part.getName() + " from " + part.getCompanyName()));
    }
}
