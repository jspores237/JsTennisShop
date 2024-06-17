package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Project: demoDarbyFrameworks2-master
 * Package: com.example.demo.domain
 * <p>
 * User: carolyn.sher
 * Date: 6/24/2022
 * Time: 3:44 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
class PartTest {
    Part partIn;
    Part partOut;

    @BeforeEach
    void setUp() {
        partIn=new InhousePart();
        partOut=new OutsourcedPart();
    }

    @Test
    void getId() {
        Long idValue=4L;
        partIn.setPartId(idValue);
        assertEquals(partIn.getPartId(), idValue);
        partOut.setPartId(idValue);
        assertEquals(partOut.getPartId(), idValue);
    }

    @Test
    void setId() {
        Long idValue=4L;
        partIn.setPartId(idValue);
        assertEquals(partIn.getPartId(), idValue);
        partOut.setPartId(idValue);
        assertEquals(partOut.getPartId(), idValue);
    }

    @Test
    void getName() {
        String name="test inhouse part";
        partIn.setName(name);
        assertEquals(name,partIn.getName());
        name="test outsourced part";
        partOut.setName(name);
        assertEquals(name,partOut.getName());
    }

    @Test
    void setName() {
        String name="test inhouse part";
        partIn.setName(name);
        assertEquals(name,partIn.getName());
        name="test outsourced part";
        partOut.setName(name);
        assertEquals(name,partOut.getName());
    }

    @Test
    void getPrice() {
        double price=1.0;
        partIn.setPrice(price);
        assertEquals(price,partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price,partOut.getPrice());
    }

    @Test
    void setPrice() {
        double price=1.0;
        partIn.setPrice(price);
        assertEquals(price,partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price,partOut.getPrice());
    }

    @Test
    void getInv() {
        int inv=5;
        partIn.setInv(inv);
        assertEquals(inv,partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv,partOut.getInv());
    }

    @Test
    void setInv() {
        int inv=5;
        partIn.setInv(inv);
        assertEquals(inv,partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv,partOut.getInv());
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
        Product product1= new Product();
        Product product2= new Product();
        Set<Product> myProducts= new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);
        partIn.setProducts(myProducts);
        assertEquals(myProducts,partIn.getProducts());
        partOut.setProducts(myProducts);
        assertEquals(myProducts,partOut.getProducts());
    }

    @Test
    void setProducts() {
        Product product1= new Product();
        Product product2= new Product();
        Set<Product> myProducts= new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);
        partIn.setProducts(myProducts);
        assertEquals(myProducts,partIn.getProducts());
        partOut.setProducts(myProducts);
        assertEquals(myProducts,partOut.getProducts());
    }

    @Test
    void testToString() {
        String name="test inhouse part";
        partIn.setName(name);
        assertEquals(name,partIn.toString());
        name="test outsourced part";
        partOut.setName(name);
        assertEquals(name,partOut.toString());
    }

    @Test
    void testEquals() {
        partIn.setPartId(1L);
        Part newPartIn=new InhousePart();
        newPartIn.setPartId(1L);
        assertEquals(partIn,newPartIn);
        partOut.setPartId(1L);
        Part newPartOut=new OutsourcedPart();
        newPartOut.setPartId(1L);
        assertEquals(partOut,newPartOut);

    }

    @Test
    void testHashCode() {
        partIn.setPartId(1L);
        partOut.setPartId(1L);
        assertEquals(partIn.hashCode(),partOut.hashCode());
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