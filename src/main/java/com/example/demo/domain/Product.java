package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS")


public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Name is required!")
    @Column(name = "NAME")
    private String name;

    @NotNull(message = "A price is required!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price value must be positive")
    @Column(name = "PRICE")
    private double price;

    @NotNull(message = "Minimum inventory is required!")
    @Min(value = 0, message = "Minimum inventory must be positive")
    @Column(name = "MIN_INV")
    private int minInv;

    @NotNull(message = "Maximum inventory is required!")
    @Min(value = 0, message = "Maximum inventory must be positive")
    @Column(name = "MAX_INV")
    private int maxInv;

    @NotNull(message = "Inventory is required!")
    @Min(value = 0, message = "Inventory value must be positive")
    @Column(name = "INV")
    private int inv;

    @ManyToMany
    @JoinTable(name = "PRODUCT_PART", joinColumns = @JoinColumn(name = "PRODUCT_ID"), inverseJoinColumns = @JoinColumn(name = "PART_ID"))
    private Set<Part> parts = new HashSet<>();

    public Product() {}

    public Product(String name, double price, int minInv, int maxInv, int inv) {
        this.name = name;
        this.price = price;
        setMinInv(minInv);
        setMaxInv(maxInv);
        setInv(inv);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinInv() {
        return minInv;
    }

    public void setMinInv(int minInv) {
        if (minInv < 0) {
            throw new IllegalArgumentException("Minimum inventory cannot be less than 0!");
        }
        if (this.maxInv != 0 && minInv > this.maxInv) {
            throw new IllegalArgumentException("Minimum inventory cannot be greater than maximum inventory!");
        }
        this.minInv = minInv;
    }

    public int getMaxInv() {
        return maxInv;
    }

    public void setMaxInv(int maxInv) {
        if (this.minInv != 0 && maxInv < this.minInv) {
            throw new IllegalArgumentException("Maximum inventory cannot be less than minimum inventory!");
        }
        this.maxInv = maxInv;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        if (this.minInv != 0 && this.maxInv != 0 && (inv < this.minInv || inv > this.maxInv)) {
            throw new IllegalArgumentException("Inventory must be between min and max values.");
        }
        this.inv = inv;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {return id.hashCode();}
}
