package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "PART")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long partId;

    @NotBlank(message = "Name is required!")
    @Column(name = "NAME")
    private String name;

    @NotNull(message = "A price is required!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price value must be positive!") //changed to DecimalMin
    @Column(name = "PRICE")
    double price;

    @NotNull(message = "Minimum inventory is required!")
    @Min(value = 0, message = "Minimum inventory cannot be less than 0!")
    @Column(name = "MIN_INV")
    int minInv;

    @NotNull(message = "Maximum inventory is required!")
    @Min(value = 0, message = "Maximum inventory cannot be less than 0!")
    @Column(name = "MAX_INV")
    int maxInv;

    @NotNull(message = "Inventory is required!")
    @Min(value = 0, message = "Inventory value must be at least 0!")
    @Column(name = "INV")
    int inv;


    @ManyToMany(mappedBy = "parts")
    private Set<Product> products = new HashSet<>();

    public Part() {}

    public Part(String name, double price, int minInv, int maxInv, int inv) {
        this.name = name;
        this.price = price;
        setMinInv(minInv);
        setMaxInv(maxInv);
        setInv(inv);
    }

    public Long getPartId() {return partId;}

    public void setPartId(Long partId) {
        this.partId = partId;
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
    public void setPrice(double price) {this.price = price;}

    public int getInv() {return inv;}

    public void setInv(int inv) {
        System.out.println("Setting inv: " + inv + ", minInv: " + this.minInv + ", maxInv: " + this.maxInv);
        if (this.minInv != 0 && this.maxInv != 0 && (inv < this.minInv || inv > this.maxInv)) {
            throw new IllegalArgumentException("Inventory must be between min and max values.");
        }
        this.inv = inv;
    }

    public int getMinInv() {
        return minInv;
    }
    public void setMinInv(int minInv) {
        System.out.println("Setting minInv: " + minInv);
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
        System.out.println("Setting maxInv: " + maxInv);
        if (this.minInv != 0 && maxInv < this.minInv) {
            throw new IllegalArgumentException("Maximum inventory cannot be less than minimum inventory!");
        }
        this.maxInv = maxInv;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public boolean isInvInvalid() {
        return this.inv < this.minInv || this.inv > this.maxInv;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return partId.equals(part.partId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId); //changed to hash code and added null check
    }
}
