package ru.h2o.shop.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class HardDrive extends Product {
    private int memorySize;
}
