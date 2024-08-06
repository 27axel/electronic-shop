package ru.h2o.shop.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("Monitor")
@Data
public class Monitor extends Product {
    private int screenSize;
}
