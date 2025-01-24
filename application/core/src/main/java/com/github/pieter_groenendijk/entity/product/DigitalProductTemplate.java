package com.github.pieter_groenendijk.entity.product;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DigitalProductTemplate extends ProductTemplate {

    @Column(name = "language", nullable = true)
    public String language;

}
