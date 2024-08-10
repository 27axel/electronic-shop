package ru.h2o.shop.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Laptop extends Product {
    private int diagonal;
}
