package com.example.demo.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("InhousePart")
public class InhousePart extends Part{

    private Long partId; //new (made id private)

    public InhousePart() {}

    public void setPartId(Long partId) {this.partId = partId;}

    public Long getPartId() {return partId;}


    public boolean isInvInvalid() {return this.inv < this.minInv || this.inv > this.maxInv;} //added

}
