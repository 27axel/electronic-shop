package ru.h2o.shop.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("HardDrive")
@Data
public class HardDrive extends Product {
    private int memorySize;
}
