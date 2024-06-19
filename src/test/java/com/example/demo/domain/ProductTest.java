package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project: demo
 * Package: com.example.demo.domain
 * <p>
 * User: carolyn.sher
 * Date: 4/28/2022
 * Time: 4:01 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
class ProductTest {
    Product product;
    @BeforeEach
    public void setUp(){
        product= new Product();
    }

    @Test
    void getId() {
        Long idValue=4L;
        product.setId(idValue);
        assertEquals(product.getId(), idValue);
    }

    @Test
    void setId() {
        Long idValue=4L;
        product.setId(idValue);
        assertEquals(product.getId(), idValue);
    }

    @Test
    void getName() {
        String name="test product";
        product.setName(name);
        assertEquals(name,product.getName());
    }

    @Test
    void setName() {
        String name="test product";
        product.setName(name);
        assertEquals(name,product.getName());
    }

    @Test
    void getPrice() {
        double price=1.0;
        product.setPrice(price);
        assertEquals(price,product.getPrice());
    }

    @Test
    void setPrice() {
        double price=1.0;
        product.setPrice(price);
        assertEquals(price,product.getPrice());
    }

    @Test
    void getInv() {
        int inv=5;
        product.setInv(inv);
        assertEquals(inv,product.getInv());
    }

    @Test
    void setInv() {
        int inv=5;
        product.setInv(inv);
        assertEquals(inv,product.getInv());
    }

    @Test
    void getParts() {
        product.setId(1L); // Set ID to avoid NullPointerException

        Part part1 = new InhousePart();
        part1.setPartId(1L);
        Part part2 = new OutsourcedPart();
        part2.setPartId(2L);
        Set<Part> parts = new HashSet<>();
        parts.add(part1);
        parts.add(part2);

        product.setParts(parts);
        assertEquals(parts, product.getParts());
    }

    @Test
    void setParts() {
        product.setId(1L); // Set ID to avoid NullPointerException

        Part part1 = new InhousePart();
        part1.setPartId(1L);
        Part part2 = new OutsourcedPart();
        part2.setPartId(2L);
        Set<Part> parts = new HashSet<>();
        parts.add(part1);
        parts.add(part2);

        product.setParts(parts);
        assertEquals(parts, product.getParts());
    }

    @Test
    void testToString() {
        String name="test product";
        product.setName(name);
        assertEquals(name,product.toString());
    }

    @Test
    void testEquals() {
        product.setId(1L);
        Product newProduct= new Product();
        newProduct.setId(1L);
        assertEquals(product,newProduct);
    }

    @Test
    void testHashCode() {
        product.setId(1L);
        Product newProduct= new Product();
        newProduct.setId(1L);
        assertEquals(product.hashCode(),newProduct.hashCode());
    }
}