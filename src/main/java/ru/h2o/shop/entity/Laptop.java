package ru.h2o.shop.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("Laptop")
@Data
public class Laptop extends Product {
    private int diagonal;
}
