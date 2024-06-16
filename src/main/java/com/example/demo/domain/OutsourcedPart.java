package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("OutsourcedPart")
public class OutsourcedPart extends Part {
    @Column(name = "COMPANY_NAME")
    private String companyName;

    public OutsourcedPart() {}

    public OutsourcedPart(String name, double price, int minInv, int maxInv, int inv, String companyName) {
        super(name, price, minInv, maxInv, inv);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
