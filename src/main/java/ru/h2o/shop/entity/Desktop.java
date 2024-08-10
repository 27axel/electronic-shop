package ru.h2o.shop.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("desktop")
@Data
public class Desktop extends Product {
    private String formFactor;
}
