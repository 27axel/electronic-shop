package ru.h2o.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.h2o.shop.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByType(String type);
}
