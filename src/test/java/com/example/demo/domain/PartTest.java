package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PartTest {
    private Part part;
    private Part partIn;
    private Part partOut;

    @BeforeEach
    public void setUp() {
        part = new Part();
        part.setPartId(1L); // Simulate database setting the ID
        part.setName("Sample Part");
        part.setPrice(10.0);
        part.setMinInv(1);
        part.setMaxInv(10);
        part.setInv(5);

        partIn = new Part();
        partOut = new Part();
    }

    @Test
    void getId() {
        Long idValue = 4L;
        partIn.setPartId(idValue);
        assertEquals(idValue, partIn.getPartId());
        partOut.setPartId(idValue);
        assertEquals(idValue, partOut.getPartId());
    }

    @Test
    void setId() {
        Long idValue = 4L;
        partIn.setPartId(idValue);
        assertEquals(idValue, partIn.getPartId());
        partOut.setPartId(idValue);
        assertEquals(idValue, partOut.getPartId());
    }

    @Test
    void getName() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.getName());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.getName());
    }

    @Test
    void setName() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.getName());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.getName());
    }

    @Test
    void getPrice() {
        double price = 1.0;
        partIn.setPrice(price);
        assertEquals(price, partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price, partOut.getPrice());
    }

    @Test
    void setPrice() {
        double price = 1.0;
        partIn.setPrice(price);
        assertEquals(price, partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price, partOut.getPrice());
    }

    @Test
    void getInv() {
        int inv = 5;
        partIn.setInv(inv);
        assertEquals(inv, partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv, partOut.getInv());
    }

    @Test
    void setInv() {
        int inv = 5;
        partIn.setInv(inv);
        assertEquals(inv, partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv, partOut.getInv());
    }

    @Test
    void getMinInv() {
        int minInv = 2;
        partIn.setMinInv(minInv);
        assertEquals(minInv, partIn.getMinInv());
        partOut.setMinInv(minInv);
        assertEquals(minInv, partOut.getMinInv());
    }

    @Test
    void setMinInv() {
        int minInv = 2;
        partIn.setMinInv(minInv);
        assertEquals(minInv, partIn.getMinInv());
        partOut.setMinInv(minInv);
        assertEquals(minInv, partOut.getMinInv());
    }

    @Test
    void getMaxInv() {
        int maxInv = 10;
        partIn.setMaxInv(maxInv);
        assertEquals(maxInv, partIn.getMaxInv());
        partOut.setMaxInv(maxInv);
        assertEquals(maxInv, partOut.getMaxInv());
    }

    @Test
    void setMaxInv() {
        int maxInv = 10;
        partIn.setMaxInv(maxInv);
        assertEquals(maxInv, partIn.getMaxInv());
        partOut.setMaxInv(maxInv);
        assertEquals(maxInv, partOut.getMaxInv());
    }

    @Test
    void getProducts() {
        partIn.setPartId(1L);
        partOut.setPartId(1L);

        Product product1 = new Product();
        product1.setId(1L); // Set ID to avoid NullPointerException
        Product product2 = new Product();
        product2.setId(2L); // Set ID to avoid NullPointerException
        Set<Product> myProducts = new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);

        partIn.setProducts(myProducts);
        assertEquals(myProducts, partIn.getProducts());

        partOut.setProducts(myProducts);
        assertEquals(myProducts, partOut.getProducts());
    }

    @Test
    void setProducts() {
        partIn.setPartId(1L);
        partOut.setPartId(1L);

        Product product1 = new Product();
        product1.setId(1L); // Set ID to avoid NullPointerException
        Product product2 = new Product();
        product2.setId(2L); // Set ID to avoid NullPointerException
        Set<Product> myProducts = new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);

        partIn.setProducts(myProducts);
        assertEquals(myProducts, partIn.getProducts());

        partOut.setProducts(myProducts);
        assertEquals(myProducts, partOut.getProducts());
    }

    @Test
    void testToString() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.toString());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.toString());
    }

    @Test
    void testEquals() {
        partIn.setPartId(1L);
        Part newPartIn = new Part();
        newPartIn.setPartId(1L);
        assertEquals(partIn, newPartIn);

        partOut.setPartId(1L);
        Part newPartOut = new Part();
        newPartOut.setPartId(1L);
        assertEquals(partOut, newPartOut);

        partIn.setPartId(null);
        newPartIn.setPartId(null);
        assertEquals(partIn, newPartIn);
    }

    private int calculateExpectedHashCode(Part part) {
        int result = 17;
        result = 31 * result + (part.getName() != null ? part.getName().hashCode() : 0);
        result = 31 * result + (part.getPartId() != null ? part.getPartId().hashCode() : 0);
        return result;
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = calculateExpectedHashCode(part);
        assertEquals(expectedHashCode, part.hashCode());
    }

    @Test
    void setMinInvShouldThrowExceptionWhenLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> partIn.setMinInv(-1));
        assertThrows(IllegalArgumentException.class, () -> partOut.setMinInv(-1));
    }

    @Test
    void setMaxInvShouldThrowExceptionWhenLessThanMinInv() {
        partIn.setMinInv(5);
        partOut.setMinInv(5);
        assertThrows(IllegalArgumentException.class, () -> partIn.setMaxInv(4));
        assertThrows(IllegalArgumentException.class, () -> partOut.setMaxInv(4));
    }

    @Test
    void setInvShouldThrowExceptionWhenLessThanMinInvOrGreaterThanMaxInv() {
        partIn.setMinInv(5);
        partIn.setMaxInv(10);
        partOut.setMinInv(5);
        partOut.setMaxInv(10);
        assertThrows(IllegalArgumentException.class, () -> partIn.setInv(4));
        assertThrows(IllegalArgumentException.class, () -> partIn.setInv(11));
        assertThrows(IllegalArgumentException.class, () -> partOut.setInv(4));
        assertThrows(IllegalArgumentException.class, () -> partOut.setInv(11));
    }
}
